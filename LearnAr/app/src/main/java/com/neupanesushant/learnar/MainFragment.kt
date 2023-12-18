package com.neupanesushant.learnar

import android.Manifest
import android.os.Bundle
import com.google.ar.sceneform.ux.ArFragment
import com.neupanesushant.learnar.arfragments.AugmentedImagesFragment
import com.neupanesushant.learnar.arfragments.BasicAugmentationFragment
import com.neupanesushant.learnar.arfragments.BuildModelFragment
import com.neupanesushant.learnar.core.BaseFragment
import com.neupanesushant.learnar.databinding.FragmentMainBinding
import com.neupanesushant.learnar.extras.PermissionHandler

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var permissionHandler: PermissionHandler

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun setupViews() {
        permissionHandler = PermissionHandler(requireActivity())
    }

    override fun setupEventListener() {
        binding.btnBasicAugmentation.setOnClickListener {
            navigateToArFragment(BasicAugmentationFragment())
        }

        binding.btnAugmentedImage.setOnClickListener {
            navigateToArFragment(AugmentedImagesFragment())
        }

        binding.btnBuildModel.setOnClickListener {
            navigateToArFragment(BuildModelFragment())
        }

    }

    private fun navigateToArFragment(fragment: ArFragment) {
        if (permissionHandler.hasPermission(Manifest.permission.CAMERA, requireContext())) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            permissionHandler.requestPermission(Manifest.permission.CAMERA)
        }
    }

    override fun setupObserver() {
    }

}