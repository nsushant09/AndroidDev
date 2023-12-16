package com.neupanesushant.learnar.extras

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object Utils {
    fun Context.show(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Context.openAppSettings() {

        val intent = Intent()
        intent.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", packageName, null)
        startActivity(intent)
    }

}