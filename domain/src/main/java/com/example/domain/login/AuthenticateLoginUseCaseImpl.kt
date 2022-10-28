package com.example.domain.login

import com.example.domain.base.ResultWrapper
import com.example.domain.utils.asFlow
import com.example.domain.utils.safeCallFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named


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
internal class AuthenticateLoginUseCaseImpl(
    private val loginRepository: LoginRepository,
    @Named("IODispatcher") private val dispatcher: CoroutineDispatcher) : AuthenticateLoginUseCase {
    override suspend fun invoke(params: AuthenticateLoginUseCase.Params): Flow<ResultWrapper<Boolean>> {
        return safeCallFlow(dispatcher) {
            loginRepository.authenticateWithServer(
                email = params.email,
                password = params.password
            )
        }
    }
}