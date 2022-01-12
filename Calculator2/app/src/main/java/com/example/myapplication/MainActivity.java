package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    Button add, sub, mult, div, eq, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num1.setOnClickListener(v -> showDisplay(v, "1"));
        num2 = findViewById(R.id.num2);
        num2.setOnClickListener(v -> showDisplay(v, "2"));
        num3 = findViewById(R.id.num3);
        num3.setOnClickListener(v -> showDisplay(v, "3"));
        num4 = findViewById(R.id.num4);
        num4.setOnClickListener(v -> showDisplay(v, "4"));
        num5 = findViewById(R.id.num5);
        num5.setOnClickListener(v -> showDisplay(v, "5"));
        num6 = findViewById(R.id.num6);
        num6.setOnClickListener(v -> showDisplay(v, "6"));
        num7 = findViewById(R.id.num7);
        num7.setOnClickListener(v -> showDisplay(v, "7"));
        num8 = findViewById(R.id.num8);
        num8.setOnClickListener(v -> showDisplay(v, "8"));
        num9 = findViewById(R.id.num9);
        num9.setOnClickListener(v -> showDisplay(v, "9"));
        num0 = findViewById(R.id.num0);
        num0.setOnClickListener(v -> showDisplay(v, "0"));
        add = findViewById(R.id.oper_add);
        add.setOnClickListener(v -> showDisplay(v, "+"));
        sub = findViewById(R.id.oper_sub);
        sub.setOnClickListener(v -> showDisplay(v, "-"));
        mult = findViewById(R.id.oper_mult);
        mult.setOnClickListener(v -> showDisplay(v, "*"));
        div = findViewById(R.id.oper_div);
        div.setOnClickListener(v -> showDisplay(v, "/"));
        eq = findViewById(R.id.oper_equal);
        eq.setOnClickListener(v -> showDisplay(v, "="));
        c = findViewById(R.id.oper_clear);
        c.setOnClickListener(v -> showDisplay(v, "C"));


    }

    String first = "0";
    String second = "";
    String operation = "+";
    StringBuilder stringall = new StringBuilder("");
    double total = 0;

    @SuppressLint({"SetTextI18n", "WrongConstant"})
    public void showDisplay(View view, String recieve_int) {
        TextView displayObj = findViewById(R.id.Display);
        TextView displayAll = findViewById(R.id.DisplayAll);
        displayObj.setTextSize(40);
        String recieved = recieve_int;
        if (!recieved.equals("C") && !recieved.equals("=")) {
            stringall.append(recieve_int);
            displayAll.setText(stringall);
        }
        if (recieved.equals("+") || recieved.equals("-") || recieved.equals("*") || recieved.equals("/") || recieved.equals("=") || recieved.equals("C")) {
            operation = recieved;
            first = total + "";
            if (operation.equals("+") || operation.equals("-")) {
                second = "0";
            } else if (operation.equals("/") || operation.equals("*")) {
                second = "0";
            } else {
                second = "";
            }

        } else {
            second += recieved;
        }

        switch (operation) {
            case "+":
                total = Double.parseDouble(first) + Double.parseDouble(second);
                displayObj.setText(total + "");
                break;

            case "-":
                total = Double.parseDouble(first) - Double.parseDouble(second);
                displayObj.setText(total + "");
                break;

            case "*":
                if (second.equals("0")) {
                    total = Double.parseDouble(first) * 1;
                } else {
                    total = Double.parseDouble(first) * Double.parseDouble(second);
                }
                displayObj.setText(total + "");
                break;

            case "/":
                if (second.equals("0")) {
                    total = Double.parseDouble(first) * 1;
                } else {
                    total = Double.parseDouble(first) / Double.parseDouble(second);
                }
                displayObj.setText(total + "");
                break;

            case "C":
                total = 0;
                first = "0";
                second = "";
                operation = "+";
                stringall.replace(0, stringall.length(), "");
                displayAll.setText(stringall);
                displayObj.setText("");
                break;

            case "=":
                displayObj.setTextSize(55);
                displayObj.setText(total + "");
                displayAll.setText("");

        }
    }
}