package com.example.data_remote.network

import com.example.data_remote.models.LoginModel
import retrofit2.http.GET
import retrofit2.http.Query


/*********************************************************
 * Class   :  LoginApi
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

interface LoginApi {

    @GET("/authentication.json")
    suspend fun authenticateEmailPassword(
        @Query("email") email: String,
        @Query("password") jobId: String
    ): LoginModel
}
