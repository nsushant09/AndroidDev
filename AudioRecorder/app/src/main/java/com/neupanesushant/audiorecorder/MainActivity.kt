package com.neupanesushant.audiorecorder

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.neupanesushant.audiorecorder.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var audioFile: File? = null
    private val audioRecorder = AndroidAudioRecorder(this)
    private val audioPlayer = AndroidAudioPlayer(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        requestAudioPermission()
        setupView()
        setupEventListener()
        setupObserver()

        if (hasExternalStoragePermission()) {
            retrieveImage()
        } else {
            requestExternalStoragePermission()
        }
    }

    fun retrieveImage() {
        val workRequest = OneTimeWorkRequestBuilder<ImageRetrievalWorker>()
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    fun setupView() {

    }


    @SuppressLint("ClickableViewAccessibility")
    fun setupEventListener() {
        var isRecording = false

        binding.btnPlayStart.setOnClickListener {
            audioFile?.let { file ->
                audioPlayer.play(file)
            }
        }

        binding.btnPlayStop.setOnClickListener {
            audioPlayer.stop()
        }

        binding.btnRecordStart.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.i(
                        "RECORDER",
                        "ACTION_DOWN ON BUTTON"
                    )
//                    val audioFileName = System.currentTimeMillis().toString() + ".mp3"
//                    File(cacheDir, audioFileName).also {
//                        audioRecorder.start(it)
//                        audioFile = it
//                    }
                }

                MotionEvent.ACTION_UP -> {
                    Log.i(
                        "RECORDER",
                        "ACTION_UP ON BUTTON"
                    )
//                    audioRecorder.stop()
                }

                MotionEvent.ACTION_CANCEL -> {
                    Log.i(
                        "RECORDER",
                        "ACTION_CANCLE ON BUTTON"
                    )
//                    audioRecorder.stop()
                }
            }
            false
        }

        binding.btnRecordStop.setOnClickListener {
            audioRecorder.stop()
            isRecording = false
        }

        binding.btnPlayOnline.setOnClickListener {
            audioPlayer.play("https://firebasestorage.googleapis.com/v0/b/kurakani-cc35b.appspot.com/o/audio%2FHigh%20Noon%20-%20TrackTribe.mp3?alt=media&token=3fe3242b-899e-48a8-94fa-fbabbf641270")
        }
    }


    fun setupObserver() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 112233 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // TODO : retrieve images
            retrieveImage()
        } else {
            requestExternalStoragePermission()
        }
    }

    private fun requestAudioPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )
    }

    private fun requestExternalStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            112233
        );
    }

    private fun hasExternalStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}