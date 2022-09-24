package com.example.utils.di

import com.example.utils.logs.Logger
import com.example.utils.logs.LoggerActive
import org.koin.dsl.module


/*********************************************************
 * Class   :  DataLocalModule
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
fun getLogger() : Logger = LoggerActive