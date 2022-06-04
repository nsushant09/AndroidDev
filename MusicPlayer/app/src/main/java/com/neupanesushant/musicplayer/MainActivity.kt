package com.neupanesushant.musicplayer

import android.app.Activity
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.neupanesushant.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var player : MediaPlayer
    private var isPause = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPlay.setOnClickListener{
            onPlayClick()
        }
        binding.btnPause.setOnClickListener{
            onPauseClick()
        }
        binding.btnStop.setOnClickListener{
            onStopClick()
        }
    }

    fun onPlayClick(){

        player = MediaPlayer.create(this, R.raw.nationalanthem)
        player.setOnCompletionListener {
            stopplayer()
        }
        player.start()
    }

    fun onPauseClick(){
        if(player.isPlaying){
            player.pause()
            isPause = true
        }
    }

    fun onStopClick(){
        stopplayer()
    }

    fun stopplayer(){
        if(player.isPlaying || isPause){
            player.release()
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        stopplayer()
    }
}