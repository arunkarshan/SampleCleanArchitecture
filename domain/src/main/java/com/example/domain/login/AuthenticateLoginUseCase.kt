package com.example.domain.login

import com.example.domain.base.BaseFlowResultWrapperUseCase


/*********************************************************
 * Class   :  AuthenticateLoginUseCase
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
interface AuthenticateLoginUseCase : BaseFlowResultWrapperUseCase<AuthenticateLoginUseCase.Params, Boolean> {
    data class Params(
        val email: String,
        val password: String
    )
}