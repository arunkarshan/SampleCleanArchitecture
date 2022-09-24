package com.example.data_local.datasource

import com.example.domain.models.LoginModelDomain
import com.example.sampleaction.repository.datasource.local.LoginLocalSource
import org.koin.core.annotation.Factory
import java.lang.Exception


/*********************************************************
 * Class   :  LoginDataSourceImpl
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@Factory
class LoginDataSourceImpl : LoginLocalSource{
    override suspend fun authenticateWithServer(email: String, password: String): LoginModelDomain {
        throw Exception("This should not be used as Login don't have local impl")
    }
}