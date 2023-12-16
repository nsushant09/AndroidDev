package com.neupanesushant.learnar.arfragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.google.ar.core.AugmentedImage
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.ux.ArFragment
import com.neupanesushant.learnar.ArCore.ArInitializer
import com.neupanesushant.learnar.ArCore.ModelManager
import com.neupanesushant.learnar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AugmentedImagesFragment : ArFragment() {
    private lateinit var arInitializer: ArInitializer
    private lateinit var modelManager: ModelManager
    private var session: Session? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arInitializer = ArInitializer(requireActivity())
        modelManager = ModelManager(requireContext())
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        initialize()
        eventListener()
    }

    private fun initialize() {
        if (arInitializer.isArAvailable()) {
            session = arInitializer.getSession()
            getSessionConfiguration(session)
        } else {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun eventListener() {
        arSceneView.scene.addOnUpdateListener {
            val frame = arSceneView.arFrame ?: return@addOnUpdateListener
            val images = frame.getUpdatedTrackables(AugmentedImage::class.java)
            for (image in images) {
                if (image.name.equals("bike")) {
                    val anchor = image.createAnchor(image.centerPose)
                    modelManager.buildModel("scene.glb") {
                        modelManager.addModel(this, anchor, it)
                    }
                }
            }
        }
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = Config(session)
        config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        config.focusMode = Config.FocusMode.AUTO
        session?.configure(config)
        this.arSceneView.setupSession(session);

        session?.let {
            CoroutineScope(Dispatchers.IO).launch {
                setupDatabase(config, session)
            }
        }

        return config;
    }

    private suspend fun setupDatabase(config: Config, session: Session) = coroutineScope {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.greenbike)
        val imageDB = AugmentedImageDatabase(session)
        imageDB.addImage("bike", bitmap)
        config.augmentedImageDatabase = imageDB
    }
}