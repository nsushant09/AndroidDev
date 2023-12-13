package com.neupanesushant.learnar

import android.annotation.SuppressLint
import android.util.Log
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.neupanesushant.learnar.Utils.show
import com.neupanesushant.learnar.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var session: Session? = null
    private lateinit var fragment: ArFragment
    override val layoutId: Int
        get() = R.layout.activity_main

    @SuppressLint("CommitTransaction")
    override fun setupViews() {
        try {
            fragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        } catch (e: Exception) {
            Log.d("TAG", "error")
        }
    }

    private fun buildModel() {

        ModelRenderable.builder()
            .setSource(
                this,
                R.raw.sofa
            )
            .build()
            .thenAccept {
                Log.d(this.javaClass.name, "Model Loaded")

            }
            .exceptionally {
                Log.d("MODEL", it.message.toString())
                Log.d(this.javaClass.name, "Model not loaded")
                null
            }
    }

    override fun setupEventListener() {
        fragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            Log.d("TAG", "Tapped Ar Plane");
        }
    }

    override fun setupObserver() {
    }

    override fun setupExtras() {
    }

    private fun checkArCoreAvailability() {
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isSupported) {
            if (availability == ArCoreApk.Availability.SUPPORTED_INSTALLED)
                createSession()
        } else {
            ArCoreApk.getInstance().requestInstall(this, true);
        }
    }

    private fun createSession() {
        session = Session(this)
        val config = Config(session)
        session!!.configure(config)
//        buildModel()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!PermissionManager.hasCameraPermission(this)) {
            this.show("Camera permission is needed to run this application");
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!PermissionManager.hasCameraPermission(this)) {
            PermissionManager.requestCameraPermission(this)
        }
        checkArCoreAvailability()
    }

    override fun onDestroy() {
        super.onDestroy()
        session?.close()
    }
}