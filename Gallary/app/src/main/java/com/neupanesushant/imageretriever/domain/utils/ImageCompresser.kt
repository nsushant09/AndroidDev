package com.neupanesushant.imageretriever.domain.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

object ImageCompresser {
    suspend fun compressImage(context: Context, inputUri: Uri): Uri? {
        return withContext(Dispatchers.Default) {
            val a = async {
                val desiredSizeKB = 200
                var quality = 85 // Initial compression quality (can be adjusted)
                var outputStream: ByteArrayOutputStream? = null
                var compressedBitmap: Bitmap? = null
                var outputUri: Uri? = null

                try {
                    // Load the original image from the input URI
                    val inputStream = context.contentResolver.openInputStream(inputUri)
                    val options = BitmapFactory.Options()
                    options.inPreferredConfig = Bitmap.Config.RGB_565
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    inputStream?.close()

                    // Compress the Bitmap while adjusting the compression quality
                    outputStream = ByteArrayOutputStream()
                    bitmap!!.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)

                    // Check if the image is already smaller than the desired size
                    if (outputStream.size() / 1024 <= desiredSizeKB) {
                        compressedBitmap = bitmap
                    } else {
                        // Iterate to find the optimal compression quality to achieve desired size
                        while (outputStream.size() / 1024 > desiredSizeKB && quality > 0) {
                            outputStream.reset()
                            quality -= 5 // Adjust the step size according to your preference
                            bitmap!!.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                        }
                        // Create the final compressed Bitmap
                        compressedBitmap = BitmapFactory.decodeByteArray(
                            outputStream.toByteArray(),
                            0,
                            outputStream.size()
                        )
                    }

                    // Save the compressed Bitmap to the cache directory
                    val cacheDir = context.cacheDir
                    val cacheFile = File.createTempFile("compressed_", ".jpg", cacheDir)
                    val fileOutputStream = FileOutputStream(cacheFile)
                    compressedBitmap?.compress(
                        Bitmap.CompressFormat.JPEG,
                        quality,
                        fileOutputStream
                    )
                    fileOutputStream.close()

                    // Get the URI of the compressed image in the cache directory
                    outputUri = Uri.fromFile(cacheFile)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    // Close streams and recycle the Bitmaps to free up resources
                    outputStream?.close()
                    compressedBitmap?.recycle()
                }

                outputUri
            }
            a.await()
        }
    }
}