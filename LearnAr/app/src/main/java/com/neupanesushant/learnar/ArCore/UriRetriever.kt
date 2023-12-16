package com.neupanesushant.learnar.ArCore

import android.content.Context
import android.net.Uri

object UriRetriever {
    fun Context.getRawUri(fileName: String): Uri = Uri.Builder()
        .scheme("android.resource")
        .authority(packageName)
        .appendPath("raw")
        .appendPath(fileName)
        .build()
}