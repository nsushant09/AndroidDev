package com.neupanesushant.jetpackworkmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import coil.compose.rememberImagePainter
import com.neupanesushant.jetpackworkmanager.ui.theme.JetpackWorkManagerTheme

/**
 * Immediate Task
 * Long Running Task
 * Referrable Task
 *
 * Work manager takes task or schedule periodic task
 * Can do synchronization each time
 * after 1 hours or so
 */

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        You can set constraints
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(
                        NetworkType.CONNECTED
                    )
                    .build()
            )
            .build()

        val colorFilterRequest = OneTimeWorkRequestBuilder<ColorFilterWorker>()
            .build()

        val workManager = WorkManager.getInstance(applicationContext)

        setContent {
            JetpackWorkManagerTheme {
                val workInfos = workManager.getWorkInfosForUniqueWorkLiveData("download")
                    .observeAsState()
                    .value

                val downloadInfo = remember(key1 = workInfos) {
                    workInfos?.find { it.id == downloadRequest.id }
                }

                val filterInfo = remember(key1 = workInfos) {
                    workInfos?.find { it.id == colorFilterRequest.id }
                }

                val imageUri by derivedStateOf {
                    val downloadUri =
                        downloadInfo?.outputData?.getString(WorkerKeys.IMAGE_URI)?.toUri()
                    val filterUri = filterInfo?.outputData?.getString(WorkerKeys.IMAGE_URI)?.toUri()
                    filterUri ?: downloadUri
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    imageUri?.let { uri ->
                        Image(
                            painter = rememberImagePainter(data = uri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            workManager.beginUniqueWork(
                                "download",
                                ExistingWorkPolicy.KEEP,
                                downloadRequest
                            ).then(colorFilterRequest).enqueue()
                        },
                        enabled = downloadInfo?.state != WorkInfo.State.RUNNING
                    ) {
                        Text(text = "Start Download")
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    when(downloadInfo?.state){
                        WorkInfo.State.RUNNING -> Text("Downloading...")
                        WorkInfo.State.SUCCEEDED -> Text("Download Succeeded")
                        WorkInfo.State.FAILED -> Text("Download Failed")
                        WorkInfo.State.CANCELLED -> Text("Download Cancled")
                        WorkInfo.State.BLOCKED -> Text("Download Blocked")
                        else -> {}
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    when(filterInfo?.state){
                        WorkInfo.State.RUNNING -> Text("Applying Filter...")
                        WorkInfo.State.SUCCEEDED -> Text("Filter Succeeded")
                        WorkInfo.State.FAILED -> Text("Filter Failed")
                        WorkInfo.State.CANCELLED -> Text("Filter Cancled")
                        WorkInfo.State.BLOCKED -> Text("Filter Blocked")
                        else -> {}
                    }
                }
            }
        }
    }
}