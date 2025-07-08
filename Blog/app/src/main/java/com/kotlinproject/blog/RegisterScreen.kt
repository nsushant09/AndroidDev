package com.kotlinproject.blog


import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseUser
import com.kotlinproject.blog.viewmodel.MainViewModel
import domain.RegistrationValidator
import domain.Utils
import domain.Validator

@Composable
fun RegisterScreen(
    context: Context,
    viewModel: MainViewModel,
    onRegisterSuccess: (FirebaseUser) -> Unit,
) {
    val font = FontFamily.Serif
    val backgroundColor = Color(0xFFF5F5F5)

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun createNewUser(email: String, password: String, phone: String, name: String) {
        Toast.makeText(context, "Creating your account", Toast.LENGTH_LONG).show()
        viewModel.register(name, email, phone, password, onSuccess = { firebaseUser ->
            onRegisterSuccess(firebaseUser)
        }, onFailure = { failureCode ->
            if (failureCode == 1) {
                Utils.showToast(context, "Could not create user")
            } else {
                Utils.showToast(context, "Could not sign in automatically. Please try to sign in")
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun validateRegistrationDetails() {
        val validator: Validator = RegistrationValidator(name, email, password)
        val (isValid, errorMessage) = validator.isValid()
        if (!isValid) {
            Utils.showToast(context, errorMessage)
            return
        }
        createNewUser(email, password, phone, name)
    }

    val onRegisterClick = {
        validateRegistrationDetails()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
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

            // Header
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("BLOG.", fontSize = 36.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Share your story",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font
                )
                Text("with us.", fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Input Fields
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Enter Your Name", fontFamily = font) },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFF2196F3)
                    )
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { new ->
                        if (new.all { it.isDigit() }) phone = new
                    },
                    placeholder = { Text("Enter Phone Number", fontFamily = font) },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFF2196F3)
                    )
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Enter Your Email", fontFamily = font) },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFF2196F3)
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Enter Password", fontFamily = font) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFF2196F3)
                    )
                )
            }

            // Disabled Login Button
            OutlinedButton(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(56.dp),
                border = BorderStroke(2.dp, Color.Red),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Register", fontFamily = font, color = Color.Gray)
            }

            // Register section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Already an user?", fontFamily = font)
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = onRegisterClick,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login", fontFamily = font, color = Color.White)
                }
            }
        }
    }
}
