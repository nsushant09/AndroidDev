package com.neupanesushant.learnar

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.neupanesushant.learnar.Utils.show
import com.neupanesushant.learnar.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var session: Session? = null
    private val arFragment = ArFragment()
    override val layoutId: Int
        get() = R.layout.activity_main

    @SuppressLint("CommitTransaction")
    override fun setupViews() {
        supportFragmentManager.beginTransaction()
            .replace(binding.arFragmentContainer.id, arFragment as Fragment)
    }

    private fun buildModel() {
        val uri = Uri.parse("android.resource://$packageName/sofa.glb")

        ModelRenderable.builder()
            .setSource(
                this,
                RenderableSource.builder().setSource(this, uri, RenderableSource.SourceType.GLB)
                    .build()
            )
            .setRegistryId(uri)
            .build()
            .thenAccept {
                this.show("model loaded successfully")
            }
            .exceptionally {
                Log.d("MODEL", it.message.toString())
                this.show("Error loading model")
                null
            }
    }

    override fun setupEventListener() {
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
        buildModel()
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