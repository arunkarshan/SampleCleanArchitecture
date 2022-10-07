package com.example.sampleaction.repository.data

import com.example.domain.base.ResultWrapper
import com.example.domain.login.LoginRepository
import com.example.sampleaction.repository.datasource.LoginDataSource
import com.example.sampleaction.repository.model.mapper.toDomain
import com.example.sampleaction.repository.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named


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
internal class LoginRepositoryImpl(
    @Named("Remote") private val loginRemoteSource: LoginDataSource
) : LoginRepository {
    override suspend fun authenticateWithServer(
        email: String,
        password: String
    ): Flow<ResultWrapper<Boolean>> {
        return loginRemoteSource.authenticateWithServer(email = email, password = password)
            .map { it.toDomain() }
    }
}