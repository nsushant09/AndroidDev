package com.example.loginui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.loginui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        viewBinding.btnLogin.setOnClickListener{
            Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_SHORT).show()
        }
        viewBinding.tvRegister.setOnClickListener{
            Toast.makeText(this, "Register",Toast.LENGTH_SHORT).show()
        }
    }
}