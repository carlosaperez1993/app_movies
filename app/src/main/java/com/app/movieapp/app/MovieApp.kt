package com.app.movieapp.app

import android.app.Application
import com.app.movieapp.BuildConfig
import com.app.movieapp.di.appModule
import com.app.movieapp.di.dataModule
import com.app.movieapp.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MovieApp)
            modules(listOf(appModule,dataModule,viewmodelModule))
        }


    }


}