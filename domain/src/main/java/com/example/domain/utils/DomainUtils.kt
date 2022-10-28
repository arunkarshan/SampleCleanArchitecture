package com.example.domain.utils

import com.example.arch.NoConnectivityException
import com.example.domain.base.ErrorType
import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDomain
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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

suspend fun <T> safeCallFlow(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Flow<T>
): Flow<ResultWrapper<T>> {
    return try {
        apiCall.invoke().map {  ResultWrapper.Success(it) }.flowOn(dispatcher)
    } catch (e: NoConnectivityException) {
        ResultWrapper.Error(e, ErrorType.NetworkError).asFlow(dispatcher)
    } catch (e1: Exception) {
        ResultWrapper.Error(e1).asFlow(dispatcher)
    }
}

suspend fun <T> safeCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (e: NoConnectivityException) {
        ResultWrapper.Error(e, ErrorType.NetworkError)
    } catch (e1: Exception) {
        ResultWrapper.Error(e1)
    }
}