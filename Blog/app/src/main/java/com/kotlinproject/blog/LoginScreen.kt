package com.kotlinproject.blog

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseUser
import com.kotlinproject.blog.viewmodel.MainViewModel

@Composable
fun LoginScreen(
    context: Context,
    viewModel: MainViewModel,
    onLoginSuccess: (FirebaseUser) -> Unit = {},
    onRegisterClick: () -> Unit
) {
    val font = FontFamily.Serif
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val onLoginClick = {
        viewModel.login(email, password) { firebaseUser ->
            if (firebaseUser != null) {
                onLoginSuccess(firebaseUser)
            } else {
                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)), // light background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "BLOG.",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Share your story",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font
                )
                Text(
                    text = "with us.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font
                )
            }

            // Inputs & Login
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    placeholder = { Text("Enter Your Email", fontFamily = font) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    placeholder = { Text("Enter Password", fontFamily = font) },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { onLoginClick() },
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login", fontSize = 18.sp, fontFamily = font, color = Color.White)
                }
            }

            // Footer with Register
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("New here?", fontFamily = font)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedButton(
                    onClick = onRegisterClick,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp),
                    border = BorderStroke(2.dp, Color(0xFF2196F3)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Register", fontFamily = font, fontSize = 16.sp)
                }
            }
        }
    }
}

