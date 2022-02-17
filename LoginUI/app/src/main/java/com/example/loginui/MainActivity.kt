package com.example.loginui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener{
            Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_SHORT).show()
        }
        tvRegister.setOnClickListener{
            Toast.makeText(this, "Register",Toast.LENGTH_SHORT).show()
        }
    }
}