@file:Suppress("UNCHECKED_CAST")

package com.example.domain.base

import com.example.domain.utils.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val error: Exception? = null, var type: ErrorType = ErrorType.GenericError) :
        ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()
}

sealed class ErrorType {
    object GenericError : ErrorType()
    object NetworkError : ErrorType()
}


fun <T, R> ResultWrapper<T>.map(mapper: (T) -> R): ResultWrapper<R> {
    return try {
        when (this) {
            is ResultWrapper.Success -> ResultWrapper.Success(mapper.invoke(value))
            else -> this as ResultWrapper<R>
        }
    } catch (e: java.lang.Exception) {
        ResultWrapper.Error(e)
    }
}

suspend fun <T, R> Flow<ResultWrapper<Iterable<T>>>.flatMapSuspend(
    dispatcher: CoroutineDispatcher,
    mapper: suspend (T) -> R
): Flow<ResultWrapper<Iterable<R>>> {
    return try {
        this.flatMapConcat {
            when (it) {
                is ResultWrapper.Success -> it.value.map { item -> mapper.invoke(item) }
                    .let { result ->
                        ResultWrapper.Success(result)
                    }
                else -> this as ResultWrapper<Iterable<R>>
            }.asFlow(dispatcher)
        }
    } catch (e: java.lang.Exception) {
        flow { emit(ResultWrapper.Error(e)) }
    }
}


suspend fun <T, R> ResultWrapper<T>.mapSuspend(mapper: suspend (T) -> R): ResultWrapper<R> {
    return try {
        when (this) {
            is ResultWrapper.Success -> ResultWrapper.Success(mapper.invoke(value))
            else -> this as ResultWrapper<R>
        }
    } catch (e: java.lang.Exception) {
        ResultWrapper.Error(e)
    }
}

fun <T> ResultWrapper<Iterable<T>>.toList(): ResultWrapper<List<T>> {
    return when (this) {
        is ResultWrapper.Success -> ResultWrapper.Success(value.toList())
        else -> this as ResultWrapper<List<T>>
    }
}

suspend fun <T> ResultWrapper<Iterable<T>>.applyEach(mapper: suspend (T) -> T): ResultWrapper<Iterable<T>> {
    return try {
        when (this) {
            is ResultWrapper.Success -> ResultWrapper.Success(
                value.map { mapper.invoke(it) }
            )
            else -> this
        }
    } catch (e: java.lang.Exception) {
        ResultWrapper.Error(e)
    }
}

suspend fun <T, R> ResultWrapper<Iterable<T>>.flatMap(mapper: suspend ((T) -> Iterable<R>?)): ResultWrapper<Iterable<R>> {
    return try {
        when (this) {
            is ResultWrapper.Success -> value.flatMap { mapper.invoke(it) ?: emptyList() }.let {
                ResultWrapper.Success(it)
            }
            else -> this as ResultWrapper<Iterable<R>>
        }
    } catch (e: java.lang.Exception) {
        ResultWrapper.Error(e)
    }
}

fun <T> ResultWrapper<T>.getValue(): T? {
    return when (this) {
        is ResultWrapper.Success -> value
        else -> null
    }
}