package com.example.sampleaction.repository.datasource

import com.example.domain.base.ResultWrapper
import com.example.sampleaction.repository.model.DataResultWrapper
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

interface LoginDataSource {
    suspend fun authenticateWithServer(email: String, password: String): Flow<DataResultWrapper<Boolean>>
}