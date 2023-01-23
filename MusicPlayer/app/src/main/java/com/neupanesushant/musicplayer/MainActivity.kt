package com.neupanesushant.musicplayer

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.neupanesushant.musicplayer.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var handler : Handler
    private lateinit var musicPlayerService: MusicPlayerService
    private var mBound = false
    private var newPosition = 0;
    private lateinit var runnable : Runnable

    private val musicServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(componentName : ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.MyBinder
            musicPlayerService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
            mBound = false
        }

    }

    override fun onStart() {
        super.onStart()
        val intentBind = Intent(this, MusicPlayerService::class.java)
        bindService(intentBind, musicServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            if (mBound) {
                musicPlayerService.onPlayClick()
                binding.seekBar.max = musicPlayerService.getPlayer().duration
                handler.postDelayed(runnable,1000)
            }
        }
        binding.btnResume.setOnClickListener {
            if (mBound) {
                musicPlayerService.onResumeClick()
                handler.postDelayed(runnable,1000)
            }
        }
        binding.btnPause.setOnClickListener {
            if (mBound) {
                musicPlayerService.onPauseClick()
                handler.removeCallbacks(runnable)
            }
        }
        binding.btnStop.setOnClickListener {
            if (mBound) {
                musicPlayerService.onStopClick()
                handler.removeCallbacks(runnable)
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvShowProgress.text = p1.toString()
                newPosition = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                musicPlayerService.changePlayerPosition(newPosition)
            }
        })

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                if(musicPlayerService.isPlayerPlaying()){
                    binding.seekBar.progress = musicPlayerService.getPlayerPosition()
                    handler.postDelayed(this, 100)
                }
            }
        }
    }

}