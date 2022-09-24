@file:Suppress("unused", "UNUSED_PARAMETER")

package com.example.utils.logs

import android.util.Log

/********************************************
 * This class is an inactive Logger
 * For Release / Tools Off Build
 *******************************************/
object LoggerInactive : Logger{
    /**
     * ******************************************
     * Method actionTo Print Stacktracce
     * ******************************************
     */
    override fun printStacktrace(e: Exception?) {}

    /**
     * ******************************************
     * Method actionTo Print Stacktracce
     * ******************************************
     */
    override fun printStacktrace(e: Throwable?) {}

    /**
     * ******************************************
     * Method actionTo Print Log
     * ******************************************
     */
    override fun printLog(message: Any?, tag: String, isError: Boolean) {}

    /**
     * ******************************************
     * Method actionTo Print Log
     * ******************************************
     */
    override fun log(message: Any?, tag: String, type: Int) {}
}