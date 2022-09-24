package com.example.data_remote.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module


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
@Module(includes = [NetworkModule::class, ApiModule::class])
@ComponentScan("com.example.data_remote")
class DataRemoteModule
