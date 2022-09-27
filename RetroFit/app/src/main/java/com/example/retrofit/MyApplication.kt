package com.example.retrofit

import android.app.Application
import com.example.retrofit.koinmodules.domainModule
import com.example.retrofit.koinmodules.netModule
import com.example.retrofit.koinmodules.overrideDomainModule
import com.example.retrofit.koinmodules.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val modules = listOf(domainModule(), netModule(Constants.BASE_URL, this), vmModule(), overrideDomainModule())
        startKoin {
            androidContext(this@MyApplication)
            allowOverride(true)
            modules(modules)
        }
    }
}