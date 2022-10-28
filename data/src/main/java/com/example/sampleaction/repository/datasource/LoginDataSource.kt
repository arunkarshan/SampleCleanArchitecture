package com.example.sampleaction.repository.datasource

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
    suspend fun authenticateWithServer(email: String, password: String): Flow<Boolean>
}