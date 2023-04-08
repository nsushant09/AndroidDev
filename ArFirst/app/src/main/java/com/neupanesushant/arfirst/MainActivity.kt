package com.neupanesushant.arfirst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException
import com.neupanesushant.arfirst.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mUserRequestedInstall = true
    private var mSession: Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        enableArBtn();
    }

    private fun enableArBtn() {
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isTransient) {
            Handler(Looper.getMainLooper()).postDelayed(
                { enableArBtn() }, 200
            )
        }

        if (availability.isSupported) {
            binding.btnEnableAr.isEnabled = true
            binding.btnEnableAr.text = "Enabled"
        } else {
            binding.btnEnableAr.isEnabled = false
            binding.btnEnableAr.text = "Disabled"
        }
    }

    override fun onResume() {
        super.onResume()
        if (!PermissionHelper.hasCameraPermission(this)) {
            PermissionHelper.requestCameraPermission(this, PermissionHelper.CODE_CAMERA)
        }

        try {
            if (mSession == null) {
                when (ArCoreApk.getInstance().requestInstall(this, mUserRequestedInstall)) {
                    ArCoreApk.InstallStatus.INSTALLED -> {
                        mSession = Session(this)
                    }
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        mUserRequestedInstall = false
                        return
                    }
                }
            }
        } catch (e: UnavailableUserDeclinedInstallationException) {
            Toast.makeText(this, "TODO: handle exception " + e, Toast.LENGTH_LONG)
                .show()
            return
        } catch (e: java.lang.Exception) {
            Toast.makeText(this, "Session Creation Failed", Toast.LENGTH_SHORT).show()
            return
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!PermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(
                this,
                "Camera permission is required to run this application",
                Toast.LENGTH_SHORT
            ).show()

            if (!shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                PermissionHelper.launchPermissionSetting(this)
            }
            finish()
        }
    }


}