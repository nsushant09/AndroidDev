package com.neupanesushant.learnfirebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.neupanesushant.learnfirebaseauthentication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToLogin.setOnClickListener {
            Intent(this, MainActivity::class.java).apply{
                startActivity(intent)
                finish()
            }
        }

        binding.btnRegister.setOnClickListener{

            when{
                TextUtils.isEmpty(binding.etEmailIdregister.text.toString().trim {it <= ' '}) -> {
                    Toast.makeText(
                        this,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.etPasswordregister.text.toString().trim {it <= ' '}) -> {
                    Toast.makeText(
                        this,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email : String = binding.etEmailIdregister.text.toString().trim {it <= ' '}
                    val password : String = binding.etPasswordregister.text.toString().trim {it <= ' '}

                    //Create an instance and create a register a use with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        OnCompleteListener<AuthResult> {task ->

                            // if registration is successfully done
                            if(task.isSuccessful){
                                val firebaseUser : FirebaseUser = task.result!!.user!!
                                Toast.makeText(
                                    this,
                                    "You are registered Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", firebaseUser.email)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(
                                    this,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }

        }

    }
}