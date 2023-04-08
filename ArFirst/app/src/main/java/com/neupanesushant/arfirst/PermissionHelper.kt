package com.neupanesushant.arfirst

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHelper {
    companion object {
        const val CODE_CAMERA = 1;

        fun hasCameraPermission(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun requestCameraPermission(context: Activity, permissionCode: Int) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(android.Manifest.permission.CAMERA),
                permissionCode
            )
        }

        fun launchPermissionSetting(context : Context){
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val uri = Uri.fromParts("package", context.applicationContext.packageName, null);
            intent.data = uri
            context.startActivity(intent)
        }
    }
}