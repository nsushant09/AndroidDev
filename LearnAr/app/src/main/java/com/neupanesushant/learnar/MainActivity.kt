package com.neupanesushant.learnar

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
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
import com.neupanesushant.learnar.UriRetriever.getRawUri
import com.neupanesushant.learnar.databinding.ActivityMainBinding
import java.util.concurrent.atomic.AtomicBoolean


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var session: Session? = null
    private lateinit var fragment: ArFragment
    private val isSetModel = AtomicBoolean(false)
    private val permissionHandler = PermissionHandler(this)
    override val layoutId: Int
        get() = R.layout.activity_main

    @SuppressLint("CommitTransaction")
    override fun setupViews() {
        permissionHandler.requestPermission(Manifest.permission.CAMERA)
    }

    override fun setupEventListener() {
    }

    private fun setupArFragment() {
        fragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
//        fragment.planeDiscoveryController.hide()
        fragment.planeDiscoveryController.setInstructionView(null)
        fragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            buildModel { renderable ->
                if (isSetModel.getAndSet(true)) {
                    addModelToScene(anchor, renderable)
                }
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
        val uri = this.getRawUri("wooden_sofa")
        ModelRenderable.builder()
            .setSource(
                this, RenderableSource.builder().setSource(
                    this,
                    uri,
                    RenderableSource.SourceType.GLB
                )
                    .setScale(0.50f)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            // On Camera Permission Granted
            permissionHandler.getPermissionCode(Manifest.permission.CAMERA) -> {
                if (isPermissionGranted(grantResults)) {
                    checkArCoreAvailability()
                    setupArFragment()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        session?.close()
    }
}