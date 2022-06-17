package com.neupanesushant.animationsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.neupanesushant.animationsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layout.animation = AnimationUtils.loadAnimation(baseContext, R.anim.rotate)
    }
}