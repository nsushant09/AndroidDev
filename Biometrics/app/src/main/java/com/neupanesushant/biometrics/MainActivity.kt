package com.neupanesushant.biometrics

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.neupanesushant.biometrics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            biometricPrompt(this, this).authenticate(getBiometricPromptInfo)
        }
    }

    private fun biometricPrompt(activity: FragmentActivity, context: Context) = BiometricPrompt(
        activity,
        ContextCompat.getMainExecutor(context),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                baseContext.showText("Authentication Succeeded")
                Intent(context, LoggedInActivity::class.java).apply{
                    startActivity(this)
                }
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                baseContext.showText("Authentication Error")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                baseContext.showText("Authentication Failed")
            }
        })

    private val getBiometricPromptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Use your fingerprint to verify the transaction")
        .setDescription("Please touch your fingerprint sensor")
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
        .setNegativeButtonText("Cancle")
        .build()

    fun Context.showText(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}