package com.example.domain.login

import com.example.domain.base.ResultWrapper
import com.example.domain.utils.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


/*********************************************************
 * Class   :  AuthenticateLoginUseCaseImpl
 * Author  :  Arun Nair
 * Created :  19/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

@Factory
internal class AuthenticateLoginUseCaseImpl(private val loginRepository: LoginRepository) : AuthenticateLoginUseCase {
    override suspend fun invoke(params: AuthenticateLoginUseCase.Params): Flow<ResultWrapper<Boolean>> {
        return loginRepository.authenticateWithServer(
            email = params.email,
            password = params.password
        )
    }
}