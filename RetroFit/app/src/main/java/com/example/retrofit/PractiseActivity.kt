package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit.databinding.ActivityPractiseBinding

class PractiseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPractiseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPractiseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}