package com.neupanesushant.learnar

import android.Manifest
import androidx.fragment.app.Fragment
import com.neupanesushant.learnar.core.AppConfig
import com.neupanesushant.learnar.core.BaseFragment
import com.neupanesushant.learnar.appcore.RouteConfig
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
            navigateToArFragment(AppConfig.getFragment(RouteConfig.BASIC_AUGMENTATION_FRAGMENT)!!)
        }

        binding.btnAugmentedImage.setOnClickListener {
            navigateToArFragment(AppConfig.getFragment(RouteConfig.AUGMENTED_IMAGES_FRAGMENT)!!)
        }

        binding.btnBuildModel.setOnClickListener {
            navigateToArFragment(AppConfig.getFragment(RouteConfig.BUILD_MODEL_FRAGMENT)!!)
        }

    }

    private fun navigateToArFragment(action: Class<out Fragment>) {
        if (permissionHandler.hasPermission(Manifest.permission.CAMERA, requireContext())) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, action, null)
                .addToBackStack(null)
                .commit()
        } else {
            permissionHandler.requestPermission(Manifest.permission.CAMERA)
        }
    }

    override fun setupObserver() {
    }

}

// Android Application for Furniture Shopping with Augmented Reality Object Placement