package com.neupanesushant.audiorecorder

import java.io.File

interface AudioPlayer {
    fun play(file: File)
    fun play(url : String)
    fun stop()
}