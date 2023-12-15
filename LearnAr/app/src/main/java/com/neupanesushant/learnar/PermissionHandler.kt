package com.neupanesushant.learnar

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import java.security.MessageDigest

class PermissionHandler(private val activity: Activity) {
    fun requestPermission(permission: String) {
        if (!hasPermission(permission, activity.applicationContext)) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                Log.i(
                    "Permission",
                    "Show Permission Rationale"
                )
            }
        }
        requestPermission(permission, activity)
    }

    private fun hasPermission(permission: String, context: Context): Boolean {
        return (ActivityCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission(permission: String, activity: Activity) {
        val permissions = arrayOf(permission)
        ActivityCompat.requestPermissions(
            activity,
            permissions,
            getPermissionCode(permission)
        )
    }

    fun getPermissionCode(input: String): Int {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        val hashString = bytes.joinToString("") { "%02x".format(it) }
        return hashString.hashCode()
    }
}