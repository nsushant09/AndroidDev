package com.neupanesushant.musicplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class MusicPlayerService : Service() {

    lateinit private var player : MediaPlayer
    private var isPause = false
    private var playerDuration = 0
    private val binder = MyBinder()


    override fun onCreate() {
        super.onCreate()

    }
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    inner class MyBinder : Binder(){
        fun getService() : MusicPlayerService = this@MusicPlayerService
    }

    fun onPlayClick(){
        try{
            if(player.isPlaying || isPause)
                return
        }catch(e : Exception){
            player = MediaPlayer.create(this, R.raw.nationalanthem)
            player.setOnCompletionListener {
                stopPlayer()
            }
            playerDuration = player.duration
            //set seek player max
            isPause = false
            player.start()
        }
    }

    fun onResumeClick(){
        if(isPause){
            val lastPosition = player.currentPosition
            player.start()
            player.seekTo(lastPosition)
            isPause = false
        }
    }

    fun onPauseClick(){
        if(player.isPlaying){
            player.pause()
            isPause = true
        }
    }

    fun onStopClick(){
        stopPlayer()
    }

    fun stopPlayer(){
        if(player.isPlaying || isPause){
            player.release()
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show()
            isPause = false
        }
    }

    fun isPlayerPlaying() : Boolean{
        try{
            return player.isPlaying
        }catch(e : Exception){
            return false
        }
    }

    fun getPlayer() : MediaPlayer{
        return player
    }
    fun getPlayerPosition() : Int{
        return player.currentPosition
    }
    fun changePlayerPosition(newPosition : Int){
        player.seekTo(newPosition)
    }

    companion object{
        val instance : MusicPlayerService
            get() = MusicPlayerService()
    }
}