package com.neupanesushant.musicplayer

import android.app.Activity
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.neupanesushant.musicplayer.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var player : MediaPlayer
    private lateinit var handler : Handler
    private lateinit var runnable : Runnable
    private var isPause = false
    var playerDuration : Int = 0
    var newPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPlay.setOnClickListener{
//            onPlayClick()
            playFromLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview115/v4/62/38/72/623872c3-76b7-39e4-22d2-f2a9c41e5d34/mzaf_5795569473118457876.plus.aac.p.m4a")
        }
        binding.btnResume.setOnClickListener{
            onResumeClick()
        }
        binding.btnPause.setOnClickListener{
            onPauseClick()
        }
        binding.btnStop.setOnClickListener{
            onStopClick()
        }
        handler = Handler()


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvShowProgress.text = p1.toString()
                newPosition = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                player.seekTo(newPosition)
            }
        })

        runnable = object : Runnable {
            override fun run() {
                if(player.isPlaying){
                    binding.seekBar.progress = player.currentPosition
                    handler.postDelayed(this, 0)
                }
            }

        }


    }

    fun onPlayClick(){

        player = MediaPlayer.create(this, R.raw.nationalanthem)
        player.setOnCompletionListener {
            stopplayer()
        }
        playerDuration = player.duration
        binding.seekBar.max = playerDuration
        isPause = false
        player.start()
        handler.postDelayed(runnable, 1000)


    }

    fun onResumeClick(){
        handler.postDelayed(runnable, 1000)
        player.start()
    }

    fun playFromLink(linkstring : String){
        player = MediaPlayer()
        player.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        try{
                player.setDataSource(linkstring)
                player.prepare()
                player.start()

        }catch (e : Exception){
            Toast.makeText(this, "Error while loading url", Toast.LENGTH_SHORT).show()
        }
        binding.seekBar.max = player.duration
        isPause = false
        handler.postDelayed(runnable, 1000)

    }

    fun onPauseClick(){
        if(player.isPlaying){
            player.pause()
            handler.removeCallbacks(runnable)
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
            isPause = false
        }
    }

    override fun onStop() {
        super.onStop()
        stopplayer()
    }
}