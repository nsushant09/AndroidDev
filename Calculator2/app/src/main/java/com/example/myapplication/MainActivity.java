package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    String first = "0";
    String second = "";
    String operation = "+";
    double total = 0;

    @SuppressLint("SetTextI18n")
    public void showDisplay(View view, String recieve_int){
        TextView displayObj = findViewById(R.id.Display);
        String recieved = recieve_int;
        if(recieved.equals("+") || recieved.equals("-") || recieved.equals("*") || recieved.equals("/") || recieved.equals("=") || recieved.equals("C")){
            operation = recieved;
            first = total + "";
            if(operation.equals("+") || operation.equals("-")){
                second = "0";
            }
            else if (operation.equals("/") || operation.equals("*")){
                second = "0";
            }
            else{
                second = "";
            }

        }
        else{
            second+=recieved;
        }

        switch (operation){
            case "+" :
                total = Double.parseDouble(first) + Double.parseDouble(second);
                displayObj.setText(total+"");
                break;

            case "-" :
                total = Double.parseDouble(first) - Double.parseDouble(second);
                displayObj.setText(total+"");
                break;

            case "*" :
                if(second.equals("0")){
                    total = Double.parseDouble(first) * 1;
                }
                else {
                    total = Double.parseDouble(first) * Double.parseDouble(second);
                }
                displayObj.setText(total+"");
                break;

            case "/" :
                if(second.equals("0")){
                    total=Double.parseDouble(first) * 1;
                }
                else{
                    total = Double.parseDouble(first) / Double.parseDouble(second);
                }
                displayObj.setText(total+"");
                break;

            case "C" :
                total = 0 ;
                first = "0";
                second = "";
                operation = "+";
                displayObj.setText(total+"");
                break;

            case "=" :
                displayObj.setText(total+"");

        }
    }

    public void onClickOne(View view){
        showDisplay(view,"1");
    }

    public void onClickTwo(View view){
        showDisplay(view,"2");
    }

    public void onClickThree(View view){
        showDisplay(view,"3");
    }

    public void onClickFour(View view){
        showDisplay(view,"4");
    }

    public void onClickFive(View view){
        showDisplay(view,"5");
    }

    public void onClickSix(View view){
        showDisplay(view,"6");
    }

    public void onClickSeven(View view){
        showDisplay(view,"7");
    }
    public void onClickEight(View view){
        showDisplay(view,"8");
    }

    public void onClickNine(View view){
        showDisplay(view,"9");
    }

    public void onClickZero(View view){
        showDisplay(view,"0");
    }

    public void onClickAdd(View view){
        showDisplay(view,"+");
    }
    public void onClickSub(View view){
        showDisplay(view, "-");
    }
    public void onClickMult(View view){
        showDisplay(view, "*");
    }
    public void onClickDiv(View view){
        showDisplay(view, "/");
    }
    public void onClickEqual(View view){
        showDisplay(view, "=");
    }
    public void onClickCancle(View view){
        showDisplay(view, "C");
    }
}