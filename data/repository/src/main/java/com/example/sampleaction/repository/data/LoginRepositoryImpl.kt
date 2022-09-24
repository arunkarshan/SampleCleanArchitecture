package com.example.sampleaction.repository.data

import com.example.domain.base.ResultWrapper
import com.example.domain.login.LoginRepository
import com.example.sampleaction.repository.datasource.remote.LoginRemoteSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


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

@Factory
internal class LoginRepositoryImpl(private val loginRemoteSource: LoginRemoteSource): LoginRepository{
    override suspend fun authenticateWithServer(email: String, password: String): Flow<ResultWrapper<Boolean>> {
        return loginRemoteSource.authenticateWithServer(email = email, password = password)
    }
}