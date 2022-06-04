package com.example.android.dessertpusher

import android.app.Application
import timber.log.Timber

class PusherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //setting up timber
        Timber.plant(Timber.DebugTree())
    }
}