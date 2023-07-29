package com.neupanesushant.audiorecorder

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.io.File
import java.util.Locale

class ImageRetrievalWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    private val TAG = "IMAGE_WORKER"

    override suspend fun doWork(): Result {
        val imageUris = mutableListOf<Uri>()

        val dcimDir = File(Environment.getExternalStorageDirectory(), "DCIM")
        if (dcimDir.exists() && dcimDir.isDirectory) {
            Log.i(TAG, "Inside dcim directory")
            subDirectory(dcimDir)
        }

        // Process the list of imageUris here or perform any other required operations

        return Result.success()
    }

    private fun subDirectory(directory: File) {
        val subdirectories = directory.listFiles()
        subdirectories?.let {
            for (subdirectory in subdirectories) {
                if (subdirectory.isDirectory) {
                    getAllImagesFromDirectory(subdirectory)
                }
            }
        }
    }

    private fun getAllImagesFromDirectory(directory: File) {
        if (!directory.isDirectory)
            return

        Log.i(TAG, directory.name)
        val files = directory.listFiles()
        files?.let {
            for (file in files) {
                Log.i(TAG, file.name)
//                if (true) {
//                    val uri = FileProvider.getUriForFile(
//                        applicationContext,
//                        applicationContext.packageName + ".provider",
//                        file
//                    )
//                    Log.i(TAG, uri.toString());
//                }
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



