package com.example.data_remote.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.arch.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

@Suppress("DEPRECATION")
class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    val isConnected: Boolean
        get() {
            val connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
}