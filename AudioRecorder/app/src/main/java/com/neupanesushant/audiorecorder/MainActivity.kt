package com.neupanesushant.audiorecorder

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.app.ActivityCompat
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
        requestAudioPermission()
        setupView()
        setupEventListener()
        setupObserver()
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
                    val audioFileName = System.currentTimeMillis().toString() + ".mp3"
                    File(cacheDir, audioFileName).also {
                        audioRecorder.start(it)
                        audioFile = it
                    }
                }
                MotionEvent.ACTION_UP -> {
                    audioRecorder.stop()
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

    private fun requestAudioPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )
    }
}