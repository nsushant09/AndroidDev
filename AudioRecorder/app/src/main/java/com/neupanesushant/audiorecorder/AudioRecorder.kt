package com.neupanesushant.audiorecorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile : File)
    fun stop()
}