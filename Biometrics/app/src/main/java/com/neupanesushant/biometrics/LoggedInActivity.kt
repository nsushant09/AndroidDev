package com.neupanesushant.biometrics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neupanesushant.biometrics.databinding.ActivityLoggedInBinding

class LoggedInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoggedInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}