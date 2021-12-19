package com.example.challenge1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @SuppressLint("SetTextI18n")
    public void onRegisterClick(View view) {
        EditText firstname = findViewById(R.id.inputFirstName);
        EditText lastname = findViewById(R.id.inputLastName);
        EditText email = findViewById(R.id.inputEmail);
        TextView showfirst = findViewById(R.id.displayFirstName);
        TextView showlast = findViewById(R.id.displaySecondName);
        TextView showemail = findViewById(R.id.displayEmail);

        showfirst.setText("First Name : "+firstname.getText().toString());
        showlast.setText("Second Name : "+lastname.getText().toString());
        showemail.setText("Email : "+ email.getText().toString());
    }
}