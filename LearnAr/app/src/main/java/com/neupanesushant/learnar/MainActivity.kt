package com.neupanesushant.learnar

import android.widget.Toast
import com.google.ar.core.ArCoreApk
import com.neupanesushant.learnar.Utils.show
import com.neupanesushant.learnar.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setupViews() {
        TODO("Not yet implemented")
    }

    override fun setupEventListener() {
        TODO("Not yet implemented")
    }

    override fun setupObserver() {
        TODO("Not yet implemented")
    }

    override fun setupExtras() {
        checkArCoreAvailability()
    }

    private fun checkArCoreAvailability() {
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isSupported) {

        } else {

        }
    }

    override fun onResume() {
        super.onResume()
        if (!PermissionManager.hasCameraPermission(this)) {
            PermissionManager.requestCameraPermission(this)
        }
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
}