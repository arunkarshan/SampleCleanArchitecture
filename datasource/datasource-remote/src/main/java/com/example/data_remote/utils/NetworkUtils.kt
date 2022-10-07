package com.example.data_remote.utils

import com.example.sampleaction.repository.model.DataErrorType
import com.example.sampleaction.repository.model.DataResultWrapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
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
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): Flow<DataResultWrapper<T>> {
    return flowOf(callApi(apiCall)).flowOn(dispatcher)
}

suspend fun <T> callApi(
    apiCall: suspend () -> T
): DataResultWrapper<T> {
    return try {
        DataResultWrapper.Success(apiCall.invoke())
    } catch (e: NoConnectivityException) {
        DataResultWrapper.Error(e, DataErrorType.NetworkError)
    } catch (e1: Exception) {
        DataResultWrapper.Error(e1)
    }
}