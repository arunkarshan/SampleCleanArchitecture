package com.example.utils.logs

import android.util.Log

/********************************************
 * This class is an inactive Logger
 * For Active / Tools On Build
 *******************************************/
object LoggerActive : Logger {
    /**
     * ******************************************
     * Method actionTo Print Stacktracce
     * ******************************************
     */
    override fun printStacktrace(e: Exception?) {
        e?.printStackTrace()
    }

    /**
     * ******************************************
     * Method actionTo Print Stacktracce
     * ******************************************
     */
    override fun printStacktrace(e: Throwable?) {
        e?.let{
            Exception(it).printStackTrace()
        }
    }

    /**
     * ******************************************
     * Method actionTo Print Log
     * ******************************************
     */
    override fun printLog(message: Any?, tag: String, isError: Boolean) {
        val logtag = "TestLog - $tag"
        if (isError)
            Log.e(logtag, message.toString())
        else
            Log.w(logtag, message.toString())
    }

    /**
     * ******************************************
     * Method actionTo Print Log
     * ******************************************
     */
    override fun log(message: Any?, tag: String, type: Int) {
        val logtag = "TestLog - $tag"
        when(type) {
            Log.VERBOSE -> Log.d(logtag, message.toString())
            Log.INFO -> Log.i(logtag, message.toString())
            Log.DEBUG -> Log.d(logtag, message.toString())
            Log.WARN -> Log.w(logtag, message.toString())
            Log.ERROR -> Log.e(logtag, message.toString())
        }
    }
}