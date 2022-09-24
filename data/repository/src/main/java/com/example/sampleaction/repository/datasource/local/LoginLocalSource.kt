package com.example.sampleaction.repository.datasource.local

import com.example.domain.models.LoginModelDomain


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

interface LoginLocalSource {
    suspend fun authenticateWithServer(email: String, password: String): LoginModelDomain
}