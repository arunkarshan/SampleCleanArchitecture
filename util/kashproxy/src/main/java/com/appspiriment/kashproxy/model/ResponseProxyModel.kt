package com.appspiriment.kashproxy.model


/*********************************************************
 * Class   :  ResponseProxyModel
 * Author  :  Arun Nair
 * Created :  28/10/2022
 *******************************************************
 * Purpose : Model class for each proxy rewrite setting
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
data class ResponseProxyModel(
    val apiHost: String,
    val path: String,
    val queries: String,
    val isResponseRewrite: Boolean,
    val responseCodeMap: HashMap<Int, Int>,
    val responseBody: String
)
