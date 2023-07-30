package com.neupanesushant.imageretriever

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.neupanesushant.imageretriever.databinding.ActivityMainBinding
import com.neupanesushant.imageretriever.domain.usecase.ImagePersistenceWorker
import com.neupanesushant.imageretriever.domain.usecase.ImageRetrievalWorker
import com.neupanesushant.imageretriever.domain.utils.PermissionManager
import com.neupanesushant.imageretriever.view.ImageAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val IMAGE_RETRIEVAL_AND_SAVE = "ImageRetrievalAndSave"
    private val workManager = WorkManager.getInstance(this)
    private val imageRetrievalRequest = OneTimeWorkRequestBuilder<ImageRetrievalWorker>()
        .build()
    private val imagePersistenceRequest = OneTimeWorkRequestBuilder<ImagePersistenceWorker>()
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        )
        .build()

    private var imageUri: ArrayList<Uri>? = null


    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupEventListener()
        setupObserver()
        requestPermissionForImageRetrieval()
    }

    private fun setupView() {
        binding.rvImages.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setupEventListener() {

    }

    private fun setupObserver() {
    }

    private fun requestPermissionForImageRetrieval() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (PermissionManager.hasMediaImagePermission(this)) {
                retrieveImage()
            } else {
                PermissionManager.requestMediaImagePermission(this)
            }
        } else {
            if (PermissionManager.hasExternalStoragePermission(this)) {
                retrieveImage()
            } else {
                PermissionManager.requestExternalStoragePermission(this)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun retrieveImage() {

        workManager.beginUniqueWork(
            IMAGE_RETRIEVAL_AND_SAVE,
            ExistingWorkPolicy.REPLACE,
            imageRetrievalRequest
        ).then(
            imagePersistenceRequest
        ).enqueue()

        workManager.getWorkInfoByIdLiveData(imageRetrievalRequest.id).observe(this) { workInfo ->
            if (workInfo?.state == WorkInfo.State.SUCCEEDED) {
                imageUri = ImageRetrievalWorker.imageUri
                binding.progressBar.isVisible = false
                setImage()
            } else {
                binding.progressBar.isVisible = true
            }
        }
    }

    private fun setImage() {
        imageUri?.let {
            val adapter = ImageAdapter(this, it) {
                Toast.makeText(this, "This feature will be added soon", Toast.LENGTH_SHORT).show()
            }
            binding.rvImages.adapter = adapter
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PermissionManager.EXTERNAL_STORAGE_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            retrieveImage()
        }

        if (requestCode == PermissionManager.EXTERNAL_STORAGE_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            retrieveImage()
        }

    }

}