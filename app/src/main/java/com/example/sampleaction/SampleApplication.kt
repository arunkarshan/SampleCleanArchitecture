package com.example.sampleaction

import android.app.Application
import com.example.data_local.di.DataLocalModule
import com.example.data_remote.di.DataRemoteModule
import com.example.domain.di.DomainModule
import com.example.sampleaction.di.PresentationModule
import com.example.sampleaction.repository.di.RepositoryModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ksp.generated.module


class SampleApplication : Application() {


    /***************************************
     * Setting Observers
     ***************************************/
    override fun onCreate() {
        super.onCreate()
        initializeKoinProviders(this)
    }

    /***************************************
     * Setting Observers
     ***************************************/
    private fun initializeKoinProviders(application: Application) {

        val appContext = module {
            single { application }
            single { application.applicationContext }
            single { application.assets }
            single { Dispatchers.Default }
            single(named("IODispatcher")) { Dispatchers.IO }
        }

        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(
                PresentationModule().module,
                DomainModule().module,
                DataRemoteModule().module,
                DataLocalModule().module,
                RepositoryModule().module,
                appContext)
        }
    }

}