package com.neupanesushant.learnar.arfragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.google.ar.core.Anchor
import com.google.ar.core.Session
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.Color
import com.google.ar.sceneform.rendering.Material
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.ux.ArFragment
import com.neupanesushant.learnar.ArCore.ArInitializer
import com.neupanesushant.learnar.ArCore.ModelManager
import com.neupanesushant.learnar.MainActivity
import com.neupanesushant.learnar.databinding.ActivityMainBinding

class BuildModelFragment : ArFragment() {

    private lateinit var activityBinding: ActivityMainBinding
    private lateinit var arInitializer: ArInitializer
    private lateinit var modelManager: ModelManager
    private var session: Session? = null

    internal enum class ShapeType {
        CUBE,
        SPHERE,
        CYLINDER
    }

    internal class ShapesFactory {
        companion object {
            fun getShape(material: Material, type: ShapeType): ModelRenderable {
                return when (type) {
                    ShapeType.CUBE -> ShapeFactory.makeCube(
                        Vector3(0.1f, 0.1f, 0.1f),
                        Vector3(0f, 0.1f, 0f),
                        material
                    )

                    ShapeType.SPHERE -> ShapeFactory.makeSphere(
                        0.1f,
                        Vector3(0f, 0.1f, 0f),
                        material
                    )

                    ShapeType.CYLINDER -> ShapeFactory.makeCylinder(
                        0.1f,
                        0.2f,
                        Vector3(0f, 0.1f, 0f),
                        material
                    )

                    else -> {
                        ShapeFactory.makeCube(
                            Vector3(0.1f, 0.1f, 0.1f),
                            Vector3(0f, 0.1f, 0f),
                            material
                        )
                    }
                }
            }
        }
    }

    private var selectedShape = ShapeType.CUBE
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arInitializer = ArInitializer(requireActivity())
        modelManager = ModelManager(requireContext())
        initialize()
        setupView()
        setupEventListener()
    }

    private fun initialize() {
        if (arInitializer.isArAvailable()) {
            session = arInitializer.getSession()
            setupPlaneListener()
        } else {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun setupPlaneListener() {
        setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            placeShape(anchor)
        }
    }

    private fun setupView() {
        val activity = requireActivity() as MainActivity
        activityBinding = activity.getActivityBinding()
        activityBinding.drawShapesLayout.isVisible = true

        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
    }

    private fun placeShape(anchor: Anchor) {
        MaterialFactory
            .makeOpaqueWithColor(requireContext(), Color(android.graphics.Color.BLUE))
            .thenAccept {
                val renderable = ShapesFactory.getShape(it, selectedShape)
                modelManager.addModel(this, anchor, renderable)
            }
    }

    private fun setupEventListener() {
        activityBinding.apply {
            btnCube.setOnClickListener { selectedShape = ShapeType.CUBE }
            btnSphere.setOnClickListener { selectedShape = ShapeType.SPHERE }
            btnCylinder.setOnClickListener { selectedShape = ShapeType.CYLINDER }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityBinding.drawShapesLayout.isVisible = false
    }
}