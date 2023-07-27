package com.neupanesushant.jetpackworkmanager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class DownloadApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "Download Channel",
            "Image Download",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}