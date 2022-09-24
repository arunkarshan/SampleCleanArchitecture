package com.example.sampleaction.utils.extentions

import androidx.lifecycle.MutableLiveData
import com.example.domain.base.BaseFlowResultWrapperUseCase
import com.example.domain.base.BaseFlowUseCase
import com.example.domain.base.ErrorType
import com.example.domain.base.ResultWrapper
import com.example.utils.di.getLogger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect


/*********************************************************
 * Class   :  FlowUseCaseUtils
 * Author  :  Arun Nair
 * Created :  16/05/22
 *******************************************************
 * Purpose :  Extn functions for FlowUseCases
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

/***************************************
 * Setting Observers
 ***************************************/
fun <P, R, T> BaseFlowResultWrapperUseCase<P, R>.collectWrapper(
    scope: CoroutineScope,
    param: P,
    data: MutableLiveData<T>? = null,
    dataTransformer: ((R) -> T?),
    successListener: ((T?) -> Unit)? = null,
    error: MutableLiveData<String?>? = null,
    errorListener: ((String?) -> Unit)? = null,
    loading: MutableLiveData<Boolean>? = null,
    loadingListener: ((Boolean) -> Unit)? = null,
    assignIfNull: Boolean = false
) {
    collectWrapper(
        scope = scope,
        param = param,
        successListener = {
            dataTransformer.invoke(it).let { result ->
                if (!(assignIfNull and (it == null))) {
                    data?.postValue(result)
                    successListener?.invoke(result)
                }
            }
        },
        error = error,
        errorListener = errorListener,
        loading = loading,
        loadingListener = loadingListener
    )
}

/***************************************
 * Setting Observers
 ***************************************/
fun <P, R> BaseFlowResultWrapperUseCase<P, R>.collectWrapper(
    scope: CoroutineScope,
    param: P,
    data: MutableLiveData<R>? = null,
    successListener: ((R) -> Unit)? = null,
    error: MutableLiveData<String?>? = null,
    errorListener: ((String?) -> Unit)? = null,
    loading: MutableLiveData<Boolean>? = null,
    loadingListener: ((Boolean) -> Unit)? = null
) {
    loading?.postValue(true)
    loadingListener?.invoke(true)
    scope.launch {
        invoke(param).catch {
            getLogger().printStacktrace(Exception(it))
            errorListener?.invoke(it.message)
            loading?.postValue(false)
            loadingListener?.invoke(false)
        }.collect { result ->
            result.handleResultWrapper(
                successListener = {
                    data?.postValue(it)
                    successListener?.invoke(it)
                },
                errorListener = {
                    error?.postValue(it)
                    errorListener?.invoke(it)
                },
                loadingListener = {
                    loading?.postValue(it)
                    loadingListener?.invoke(it)
                }
            )
        }
    }
}


/***************************************
 * Setting Observers
 ***************************************/
fun <P, R, T> BaseFlowUseCase<P, R>.transformCollect(
    scope: CoroutineScope,
    param: P,
    data: MutableLiveData<T>? = null,
    dataTransformer: ((R) -> T?),
    successListener: ((T?) -> Unit)? = null,
    error: MutableLiveData<String?>? = null,
    errorListener: ((String?) -> Unit)? = null,
    loading: MutableLiveData<Boolean>? = null,
    loadingListener: ((Boolean) -> Unit)? = null,
    assignIfNull: Boolean = false
) {
    collect(
        scope = scope,
        param = param,
        successListener = {
            if (!(assignIfNull and (it == null))) {
                val result = dataTransformer.invoke(it)
                data?.postValue(result)
                successListener?.invoke(result)
            }
        },
        error = error,
        errorListener = errorListener,
        loading = loading,
        loadingListener = loadingListener
    )
}


/***************************************
 * Setting Observers
 ***************************************/
fun <P, R> BaseFlowUseCase<P, R>.collect(
    scope: CoroutineScope,
    param: P,
    data: MutableLiveData<R>? = null,
    successListener: ((R) -> Unit)? = null,
    error: MutableLiveData<String?>? = null,
    errorListener: ((String?) -> Unit)? = null,
    loading: MutableLiveData<Boolean>? = null,
    loadingListener: ((Boolean) -> Unit)? = null
) {
    collect(
        scope = scope,
        param = param,
        successListener = {
            data?.postValue(it)
            successListener?.invoke(it)
        },
        errorListener = {
            error?.postValue(it)
            errorListener?.invoke(it)
        },
        loadingListener = {
            loading?.postValue(it)
            loadingListener?.invoke(it)
        }
    )
}


/***************************************
 * Setting Observers
 ***************************************/
private fun <P, R> BaseFlowUseCase<P, R>.collect(
    scope: CoroutineScope,
    param: P,
    successListener: (R) -> Unit,
    errorListener: ((String?) -> Unit)? = null,
    loadingListener: ((Boolean) -> Unit)? = null
) {
    loadingListener?.invoke(true)
    scope.launch {
        try {
            invoke(param).catch {
                throw it
            }.collect {
                successListener.invoke(it)
                delay(100)
                loadingListener?.invoke(false)
            }
        } catch (e: java.lang.Exception) {
            getLogger().printStacktrace(e)
            errorListener?.invoke(e.message)
            loadingListener?.invoke(false)
        }
    }
}

/***************************************
 * Setting Observers
 ***************************************/
suspend fun <T> ResultWrapper<T>.handleResultWrapper(
    successListener: (T) -> Unit,
    errorListener: ((String?) -> Unit)? = null,
    loadingListener: ((Boolean) -> Unit)? = null
) {
    when (this) {
        is ResultWrapper.Error -> {
            when (type) {
                ErrorType.GenericError -> {
                    loadingListener?.invoke(false)
                    getLogger().printStacktrace(error)
                    val msg = error?.message ?: "Error occured"
                    errorListener?.invoke(msg)
                }
                ErrorType.NetworkError -> {
                    loadingListener?.invoke(false)
                    errorListener?.invoke("Network error occurred!")
                }
            }
        }
        is ResultWrapper.Loading -> {
            loadingListener?.invoke(true)
        }
        is ResultWrapper.Success -> {
            successListener.invoke(this.value)
            delay(500)
            loadingListener?.invoke(false)
        }
    }
}