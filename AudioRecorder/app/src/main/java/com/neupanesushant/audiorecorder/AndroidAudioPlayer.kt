package com.neupanesushant.audiorecorder

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File


class AndroidAudioPlayer(private val context: Context) : AudioPlayer {

    private var mediaPlayer: MediaPlayer? = null
    override fun play(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            mediaPlayer = this
            try {
                start()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun play(url: String) {
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepareAsync()
        }.setOnPreparedListener {
            it.start()
            mediaPlayer = it
        }
    }

    override fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        try {
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
        }
    }
}