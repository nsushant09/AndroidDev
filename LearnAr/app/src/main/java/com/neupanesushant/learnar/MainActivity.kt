package com.neupanesushant.learnar

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.net.Uri
import android.util.Log
import com.google.ar.core.Anchor
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
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


    override fun setupEventListener() {
        fragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            buildModel { renderable ->
                addModelToScene(anchor, renderable)
            }
        }
    }

    private fun addModelToScene(anchor: Anchor, renderable: ModelRenderable) {
        val node = AnchorNode(anchor)
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.setParent(node)
        transformableNode.renderable = renderable
        fragment.arSceneView.scene.addChild(node)
        transformableNode.select()
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
    }

    private fun buildModel(onModelBuilt: (ModelRenderable) -> Unit) {
        val uri = getRawUri("sofa")
        ModelRenderable.builder()
            .setSource(
                this, RenderableSource.builder().setSource(
                    this,
                    uri,
                    RenderableSource.SourceType.GLB
                )
                    .setScale(0.75f)  // Scale the original model to 75%
                    .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                    .build()
            )
            .setRegistryId(uri.toString())
            .build()
            .thenAccept {
                Log.d("MODEL", "Model Loaded")
                onModelBuilt(it)
            }
            .exceptionally {
                Log.e("MODEL", "Exception loading model: $it")
                AlertDialog.Builder(this)
                    .setMessage("Could not load model")
                    .show()
                null
            }
    }

    private fun getRawUri(fileName: String): Uri = Uri.Builder()
        .scheme("android.resource")
        .authority(packageName)
        .appendPath("raw")
        .appendPath(fileName)
        .build()

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