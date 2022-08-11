package com.neupanesushant.learnvideoplayer


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.neupanesushant.learnvideoplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.vv)
        val onlineUri = Uri.parse("https://cdn.videvo.net/videvo_files/video/premium/video0031/small_watermarked/512_512-0154_preview.webm")
        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.offline_test_video}")
        binding.vv.setMediaController(mediaController)
        binding.vv.setVideoURI(onlineUri)
        binding.vv.requestFocus()
        binding.vv.start()
    }
}