package com.neupanesushant.learnar.arfragments

import android.os.Bundle
import android.view.View
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment
import com.neupanesushant.learnar.ArCore.ArInitializer
import com.neupanesushant.learnar.ArCore.ModelManager
import com.neupanesushant.learnar.extras.Utils.show

class BasicAugmentationFragment : ArFragment() {

    private lateinit var arInitializer: ArInitializer
    private lateinit var modelManager: ModelManager

    private var session: Session? = null
    private var isModelSet = false;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arInitializer = ArInitializer(requireActivity())
        modelManager = ModelManager(requireContext())
        setupAr()
    }

    private fun setupAr() {
        if (arInitializer.isArAvailable()) {
            session = arInitializer.getSession()
            setupFragment()
        } else {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun setupFragment() {
        this.planeDiscoveryController.hide()
        this.planeDiscoveryController.setInstructionView(null)
        this.setOnTapArPlaneListener { hitResult, _, _ ->
            if (isModelSet) return@setOnTapArPlaneListener

            val anchor = hitResult.createAnchor()
            modelManager.buildModel("scene") {
                modelManager.addModelToScene(this, anchor, it)
            }
            requireContext().show("Displaying Object")
            isModelSet = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        session?.close()
    }
}