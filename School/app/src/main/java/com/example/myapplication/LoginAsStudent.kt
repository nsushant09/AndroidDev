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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

val storeAuth = mutableListOf<String>()


class LoginAsStudent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emptyStoreAuthList()
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
                val studentProfilePageIntent = Intent(this,StudentMainActivity::class.java)
                startActivity(studentProfilePageIntent)
                finish()
                storeAuth.add(userNameInput.text.toString())
                storeAuth.add(passwordInput.text.toString())
            }else{
                Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_LONG).show()
            }
        }

    }

    object StaticMethods{
        fun returnAccountIndex() : Int = Student.StaticMethods.loggedInIndex(storeAuth.get(0),storeAuth.get(1))
    }

    fun emptyStoreAuthList(){
        for(i in storeAuth.size-1 downTo 0){
            storeAuth.removeAt(i)
        }
    }


}