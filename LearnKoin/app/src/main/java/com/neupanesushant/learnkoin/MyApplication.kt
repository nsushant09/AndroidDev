package com.neupanesushant.learnkoin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            //this is used to inject application context with koin
            androidContext(this@MyApplication)
            modules(appModule, activityModule)
        }
    }
}