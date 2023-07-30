package com.neupanesushant.imageretriever.domain.usecase

import android.content.Context
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.neupanesushant.imageretriever.data.datasource.FirebaseInstance
import com.neupanesushant.imageretriever.domain.utils.ImageCompresser
import com.neupanesushant.imageretriever.domain.utils.SystemInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ImagePersistenceWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private lateinit var imageUri: ArrayList<Uri>
    private val CHUNK_SIZE = 2
    override suspend fun doWork(): Result {
        imageUri = ImageRetrievalWorker.imageUri

        return withContext(Dispatchers.IO) {
            try {
                val userid = SystemInfo.getDeviceId(context)
                val reference = FirebaseInstance.firebaseStorage.getReference("/images/$userid/")

                val chunks = imageUri.chunked(CHUNK_SIZE)

                chunks.map { chunk ->
                    async {
                        chunk.map { uri ->
                            ImageCompresser.compressImage(context, uri)?.let {
                                reference.child(uri.toString().substringAfterLast("/"))
                                    .putFile(it)
                            }
                        }
                    }
                }

                Result.success()
            } catch (e: Exception) {
                Result.retry()
            }
        }
    }

}