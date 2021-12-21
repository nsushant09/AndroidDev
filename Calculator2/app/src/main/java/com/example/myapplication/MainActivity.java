package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDisplay(View view,StringBuilder recieve_int){
        TextView displayObj = findViewById(R.id.Display);
        StringBuilder string = new StringBuilder();
        string.append(recieve_int);
        double number = Double.parseDouble(string.toString());
        double total = 0;
    }

    public void onClickOne(View view){
        StringBuilder string_one = new StringBuilder("1");
        showDisplay(view,string_one);
    }

    public void onClickTwo(View view){
        StringBuilder string_two = new StringBuilder("2");
        showDisplay(view,string_two);
    }

    public void onClickThree(View view){
        StringBuilder string_three = new StringBuilder("3");
        showDisplay(view,string_three);
    }

    public void onClickFour(View view){
        StringBuilder string_four = new StringBuilder("4");
        showDisplay(view,string_four);
    }

    public void onClickFive(View view){
        StringBuilder string_five = new StringBuilder("1");
        showDisplay(view,string_five);
    }

    public void onClickSix(View view){
        StringBuilder string_six = new StringBuilder("6");
        showDisplay(view,string_six);
    }

    public void onClickSeven(View view){
        StringBuilder string_seven = new StringBuilder("7");
        showDisplay(view,string_seven);
    }
    public void onClickEight(View view){
        StringBuilder string_eight = new StringBuilder("8");
        showDisplay(view,string_eight);
    }

    public void onClickNine(View view){
        StringBuilder string_nine = new StringBuilder("9");
        showDisplay(view,string_nine);
    }

    public void onClickZero(View view){
        StringBuilder string_zero = new StringBuilder("0");
        showDisplay(view,string_zero);
    }

}