package com.example.data_remote.utils

import com.example.data_remote.R
import com.example.domain.base.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import java.lang.Exception


/*********************************************************
 * Class   :  NetworkUtils
 * Author  :  Arun Nair
 * Created :  23/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

suspend fun <T> callApiFlow(
    apiCall: suspend () -> T,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): Flow<ResultWrapper<T>> {
    return withContext(dispatcher) {
        flowOf(
            try {
                val api = async { apiCall.invoke() }
                ResultWrapper.Success(api.await())
            } catch (e: NoConnectivityException) {
                ResultWrapper.Error(e)
            }
        )
    }
}

suspend fun <T> callApi(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val api = async { apiCall.invoke() }
            ResultWrapper.Success(api.await())
        } catch (e: NoConnectivityException) {
            ResultWrapper.Error(e)
        }
    }
}