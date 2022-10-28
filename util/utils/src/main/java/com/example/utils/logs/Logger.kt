package com.example.utils.logs

import android.util.Log

/********************************************
 * This class is an inactive Logger
 * For Active / Tools On Build
 *******************************************/
interface Logger {
    fun printStacktrace(e: Exception?)
    fun printStacktrace(e: Throwable?)
    fun printLog(message: Any?, tag: String = "LogTag: ", isError: Boolean = false)
    fun log(message: Any?, tag: String = "LogTag: ", type: Int = Log.WARN)
}