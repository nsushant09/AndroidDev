package com.neupanesushant.learnar.ArCore

import android.app.Activity
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session

class ArInitializer(private val activity: Activity) {
    fun isArAvailable(): Boolean {
        val availability = ArCoreApk.getInstance().checkAvailability(activity.baseContext)
        return availability.isSupported && availability == ArCoreApk.Availability.SUPPORTED_INSTALLED
    }

    fun onArAvailable(onArCoreAvailable: () -> Unit) {
        val availability = ArCoreApk.getInstance().checkAvailability(activity.baseContext)
        if (availability.isSupported && availability == ArCoreApk.Availability.SUPPORTED_INSTALLED) {
            onArCoreAvailable()
        } else {
            requestInstall()
        }
    }

    fun requestInstall() {
        ArCoreApk.getInstance().requestInstall(activity, true);
    }

    fun getSession() : Session{
        val session = Session(activity.baseContext)
        val config = Config(session)
        session.configure(config)
        return session
    }
}