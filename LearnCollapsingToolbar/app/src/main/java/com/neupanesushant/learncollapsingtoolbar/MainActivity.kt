package com.neupanesushant.learncollapsingtoolbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neupanesushant.learncollapsingtoolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var downloadManager: DownloadFileUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        downloadManager = DownloadFileUseCase(this)

        setupView()
        setupEventListener()
        setupObserver()
    }

    private fun setupView() {
        downloadManager.downloadData(
            "https://www.youtube.com/watch?v=vN0optTElms",
            ".mp4",
            "Downloading",
            "Downloading"
        )
    }

    private fun setupEventListener() {

    }

    private fun setupObserver() {

    }

    fun youTubeGetID(url: String): String {
        val regex = Regex("(vi/|v=|/v/|youtu\\.be/|/embed/)")

        val urlParts = url.split(regex).toTypedArray()
        return if (urlParts.size > 2) {
            urlParts[2].split(Regex("[^0-9a-z_\\-]", RegexOption.IGNORE_CASE))[0]
        } else {
            urlParts[0]
        }
    }


}