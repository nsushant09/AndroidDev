package com.neupanesushant.learnfirebaseauthentication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.neupanesushant.learnfirebaseauthentication.databinding.ActivityLoginBinding
import com.neupanesushant.learnfirebaseauthentication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            gotoLoginActivity()
        }
        binding.btnRegister.setOnClickListener{
            Intent(this, RegisterActivity::class.java).apply{
                startActivity(this)
            }
        }

        binding.btnLogin.setOnClickListener {

            when{
                TextUtils.isEmpty(binding.etEmailId.text.toString().trim {it <= ' '}) -> {
                    Toast.makeText(
                        this,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.etPassword.text.toString().trim {it <= ' '}) -> {
                    Toast.makeText(
                        this,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {

                    val email : String = binding.etEmailId.text.toString().trim {it <= ' '}
                    val password : String = binding.etPassword.text.toString().trim {it <= ' '}

                    //log in using firebase
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "You are logged in successfully",Toast.LENGTH_SHORT).show()
                            gotoLoginActivity()
                        }else{
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }

        }



    }



    fun gotoLoginActivity(){
        Intent(this, LoginActivity::class.java).apply {
            putExtra("user_id",auth.currentUser!!.uid)
            putExtra("email_id", auth.currentUser!!.email)
            startActivity(this)
            finish()
        }
    }
}