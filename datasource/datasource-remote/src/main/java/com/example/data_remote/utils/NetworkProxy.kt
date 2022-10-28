package com.example.data_remote.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkProxy() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        val apiResponse = chain.proceed(builder.build())

        val response = apiResponse.let {
            Response.Builder().apply {
                code(400)
                message(it.message)
                request(it.request)
                protocol(it.protocol)
                handshake(it.handshake)
                headers(it.headers)
                body(it.body)
                networkResponse(it.networkResponse)
                cacheResponse(it.cacheResponse)
                priorResponse(it.priorResponse)
                sentRequestAtMillis(it.sentRequestAtMillis)
                receivedResponseAtMillis(it.receivedResponseAtMillis)
            }
        }
        return response.build()
    }
}