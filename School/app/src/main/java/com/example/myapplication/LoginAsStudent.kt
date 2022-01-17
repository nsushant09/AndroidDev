package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class LoginAsStudent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_as_student)
        val backImage : ImageView = findViewById(R.id.btnBackImage)
        backImage.setOnClickListener{
            val backIntent = Intent(this,Login::class.java)
            startActivity(backIntent)
            finish()
        }
        val userNameInput : EditText = findViewById(R.id.usernameInput)
        val passwordInput : EditText = findViewById(R.id.passwordInput)
        val btnLogin : Button = (findViewById(R.id.btnLogin))

        btnLogin.setOnClickListener{
            if(Student.StaticMethods.passwordAuthenticationSuccess(userNameInput.text.toString(), passwordInput.text.toString())){
                Toast.makeText(this,"Logged In ",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_LONG).show()
                passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }


    }


}