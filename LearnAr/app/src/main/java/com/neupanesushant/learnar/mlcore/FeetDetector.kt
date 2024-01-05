package com.neupanesushant.learnar.mlcore

import android.content.Context
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.core.BaseOptions.DelegateOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetector

class FeetDetector(private val context: Context) {
    val FeetDetectorModelToken = "1"
    val baseOptions = BaseOptions.builder()
        .setDelegateOptions(
            DelegateOptions.GpuOptions.builder().setModelToken(FeetDetectorModelToken).build()
        )
        .setModelAssetPath("")
        .build()

    val optionsBuilder = ObjectDetector.ObjectDetectorOptions.builder()
        .setScoreThreshold(30f)
        .setRunningMode(RunningMode.LIVE_STREAM)
        .setBaseOptions(baseOptions)

    val options = optionsBuilder.build()

    val objectDetector = ObjectDetector.createFromOptions(context, options)


}