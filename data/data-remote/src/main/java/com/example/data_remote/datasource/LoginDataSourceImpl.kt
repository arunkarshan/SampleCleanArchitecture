package com.example.data_remote.datasource

import com.example.data_remote.network.LoginApi
import com.example.data_remote.utils.callApi
import com.example.domain.base.ResultWrapper
import com.example.domain.base.map
import com.example.sampleaction.repository.datasource.remote.LoginRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
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
class LoginDataSourceImpl(
    private val loginApi: LoginApi,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginRemoteSource {
    override suspend fun authenticateWithServer(
        email: String,
        password: String
    ): Flow<ResultWrapper<Boolean>> {
        return flow {
            callApi(dispatcher) { loginApi.authenticateEmailPassword(email, password) }.map {
                if (it.email != email) throw Exception("No user found!")
                else if (it.password != password) throw Exception("Invalid credentials!")
                else true
            }.let {
                 emit(it)
            }
        }
    }
}