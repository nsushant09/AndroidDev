package com.neupanesushant.imageretriever.domain.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Locale

class ImageRetrievalWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        val imageUri = arrayListOf<Uri>()
    }

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val dcimDir = File(Environment.getExternalStorageDirectory(), "DCIM")
            if (dcimDir.exists() && dcimDir.isDirectory) {
                subDirectory(dcimDir)
            }

            Result.success()
        }
    }

    private suspend fun subDirectory(directory: File) {
        withContext(Dispatchers.IO) {
            val subdirectories = directory.listFiles()
            subdirectories?.let {
                for (subdirectory in subdirectories) {
                    if (subdirectory.isDirectory) {
                        getAllImagesFromDirectory(subdirectory)
                    }
                }
            }
        }
    }

    private suspend fun getAllImagesFromDirectory(directory: File) {
        withContext(Dispatchers.IO) {
            if (!directory.isDirectory) return@withContext

            val files = directory.listFiles()
            files?.let {
                for (file in files) {
                    if (isImageFile(file)) {
                        val uri = FileProvider.getUriForFile(
                            applicationContext, applicationContext.packageName + ".provider", file
                        )
                        imageUri.add(uri)
                    }
                }
            }
        }
    }

    private fun isImageFile(file: File): Boolean {
        val imageExtensions = arrayOf("jpg", "jpeg", "png", "gif", "bmp")

        for (extension in imageExtensions) {
            if (file.name.toLowerCase(Locale.ROOT).endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}



