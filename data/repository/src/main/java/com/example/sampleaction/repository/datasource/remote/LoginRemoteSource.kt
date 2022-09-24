package com.example.sampleaction.repository.datasource.remote

import com.example.domain.base.ResultWrapper
import kotlinx.coroutines.flow.Flow


/*********************************************************
 * Class   :  LoginLoacalSource
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

interface LoginRemoteSource {
    suspend fun authenticateWithServer(email: String, password: String): Flow<ResultWrapper<Boolean>>
}