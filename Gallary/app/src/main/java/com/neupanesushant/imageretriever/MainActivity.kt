package com.neupanesushant.imageretriever

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.neupanesushant.imageretriever.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mediaImagePermission = 112233;
    private val externalStoragePermission = 123;

    private val workManager = WorkManager.getInstance(this)
    private val imageRetrievalRequest = OneTimeWorkRequestBuilder<ImageRetrievalWorker>()
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.retrieveImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (hasMediaImagePermission()) {
                    retrieveImage()
                } else {
                    requestMediaImagePermission()
                }
            } else {
                if (hasExternalStoragePermission()) {
                    retrieveImage()
                } else {
                    requestExternalStoragePermission()
                }
            }
        }


    }

    var imageUri: List<Uri>? = null
    @SuppressLint("SetTextI18n")
    private fun retrieveImage() {
        Log.i("TAG", "Permission Granted");

        workManager.enqueue(imageRetrievalRequest)
        workManager.getWorkInfoByIdLiveData(imageRetrievalRequest.id).observe(this){workInfo ->
            if(workInfo?.state == WorkInfo.State.SUCCEEDED) {
                binding.retrieveImage.text = "Completed"
                imageUri = ImageRetrievalWorker.imageUri
                setImage()
            }else{
                binding.retrieveImage.text = "Retrieving..."
            }
        }
    }

    fun setImage() {
        imageUri?.let {
            if (imageUri!!.size > 0) {
                binding.displayImage.setImageURI(imageUri!!.get(0))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == externalStoragePermission && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            retrieveImage()
        }

        if (requestCode == mediaImagePermission && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            retrieveImage()
        }

    }


    private fun requestExternalStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            externalStoragePermission
        );
    }

    private fun hasExternalStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestMediaImagePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            mediaImagePermission
        );
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasMediaImagePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
    }

}