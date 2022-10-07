package com.example.data_local.datasource

import com.example.sampleaction.repository.datasource.LoginDataSource
import com.example.sampleaction.repository.model.DataResultWrapper
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
@Named("Local")
class LoginDataSourceImpl : LoginDataSource{
    override suspend fun authenticateWithServer(email: String, password: String): Flow<DataResultWrapper<Boolean>> {
        throw Exception("This should not be used as Login don't have local impl")
    }
}