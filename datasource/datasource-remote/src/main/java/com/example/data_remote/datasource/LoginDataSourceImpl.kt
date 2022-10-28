package com.example.data_remote.datasource

import com.example.data_remote.network.LoginApi
import com.example.sampleaction.repository.datasource.LoginDataSource
import com.example.sampleaction.repository.utils.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named


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
@Named("Remote")
class LoginDataSourceImpl(
    private val loginApi: LoginApi,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginDataSource {
    override suspend fun authenticateWithServer(
        email: String,
        password: String
    ): Flow<Boolean> {
        return loginApi.authenticateEmailPassword(email, password).let {
            if (it.email != email) throw Exception("No user found!")
            else if (it.password != password) throw Exception("Invalid credentials!")
            else true
        }.asFlow(dispatcher)
    }
}