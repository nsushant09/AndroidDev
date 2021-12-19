package com.example.calculator;

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
    public void showResult(View view,String title,String num){
        TextView showResult = findViewById(R.id.Result);
        showResult.setText(title+num);
    }
    @SuppressLint("SetTextI18n")
    public void onButtonClick(View view){
        EditText first = findViewById(R.id.First);
        EditText second = findViewById(R.id.Second);

        int firstnum = Integer.parseInt(String.valueOf(first.getText()));
        int secondnum = Integer.parseInt(String.valueOf(second.getText()));
        int total = firstnum + secondnum;
        showResult(view,"The addition is : ",String.valueOf(total));
    }

    public void onSubClick(View view){

        EditText first = findViewById(R.id.First);
        EditText second = findViewById(R.id.Second);
        int firstnum = Integer.parseInt(String.valueOf(first.getText()));
        int secondnum = Integer.parseInt(String.valueOf(second.getText()));
        int total = firstnum - secondnum;
        showResult(view,"The subtraction is : ",String.valueOf(total));

    }
    public void onMultClick(View view){
        EditText first = findViewById(R.id.First);
        EditText second = findViewById(R.id.Second);
        int firstnum = Integer.parseInt(String.valueOf(first.getText()));
        int secondnum = Integer.parseInt(String.valueOf(second.getText()));
        int total = firstnum * secondnum;
        showResult(view,"The multiplication is : ",String.valueOf(total));
    }

    public void onDivClick(View view){
        EditText first = findViewById(R.id.First);
        EditText second = findViewById(R.id.Second);
        int firstnum = Integer.parseInt(String.valueOf(first.getText()));
        int secondnum = Integer.parseInt(String.valueOf(second.getText()));
        int total = firstnum / secondnum;
        showResult(view,"The division is : ",String.valueOf(total));

    }

}