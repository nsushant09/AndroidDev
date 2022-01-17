package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLoginStudent : Button= findViewById(R.id.btnLoginStudent)
        val btnLoginAdmin : Button = findViewById(R.id.btnLoginAdmin)
        val gotoLoginasStudent = Intent(this,LoginAsStudent::class.java)
        btnLoginStudent.setOnClickListener{
            startActivity(gotoLoginasStudent)
            finish()
        }
    }
}