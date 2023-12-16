package com.neupanesushant.learnar

import android.Manifest
import com.neupanesushant.learnar.arfragments.BasicAugmentationFragment
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
            if (permissionHandler.hasPermission(Manifest.permission.CAMERA, requireContext())) {
                val fragment = BasicAugmentationFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            } else {
                permissionHandler.requestPermission(Manifest.permission.CAMERA)
            }
        }
    }

    override fun setupObserver() {
    }

}