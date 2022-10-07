@file:Suppress("UNCHECKED_CAST")

package com.example.sampleaction.repository.model

import com.example.domain.base.ResultWrapper
import com.example.sampleaction.repository.model.mapper.toDomain
import java.lang.RuntimeException

sealed class DataResultWrapper<out T> {
    data class Success<out T>(val value: T) : DataResultWrapper<T>()
    data class Error(
        val error: Exception? = null,
        var type: DataErrorType = DataErrorType.GenericError
    ) : DataResultWrapper<Nothing>() {
          companion object{
              fun from(message: String): Error {
                  return DataResultWrapper.Error(RuntimeException(message))
              }
          }
    }

    object Loading : DataResultWrapper<Nothing>()
}

sealed class DataErrorType {
    object GenericError : DataErrorType()
    object NetworkError : DataErrorType()
}


fun <T, R> DataResultWrapper<T>.mapToDomain(mapperFunction: ((T) -> R)): ResultWrapper<R> {
    return when (this) {
        is DataResultWrapper.Success -> ResultWrapper.Success(value = mapperFunction.invoke(value))
        is DataResultWrapper.Error -> ResultWrapper.Error(error = error, type = type.toDomain())
        is DataResultWrapper.Loading -> ResultWrapper.Loading
    }
}

fun <T> DataResultWrapper<T>.toDomain(): ResultWrapper<T> {
    return when (this) {
        is DataResultWrapper.Success -> ResultWrapper.Success(value = value)
        is DataResultWrapper.Error -> ResultWrapper.Error(error = error, type = type.toDomain())
        is DataResultWrapper.Loading -> ResultWrapper.Loading
    }
}