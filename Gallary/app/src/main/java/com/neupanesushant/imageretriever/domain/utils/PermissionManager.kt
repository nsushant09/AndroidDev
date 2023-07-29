package com.neupanesushant.imageretriever.domain.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionManager {

    const val EXTERNAL_STORAGE_CODE = 1
    const val MEDIA_IMAGE_CODE = 2
    fun requestExternalStoragePermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_CODE
        );
    }

    fun hasExternalStoragePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestMediaImagePermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            MEDIA_IMAGE_CODE
        );
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun hasMediaImagePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
    }
}