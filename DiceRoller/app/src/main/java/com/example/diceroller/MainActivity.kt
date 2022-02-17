package com.example.diceroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diceObject : Dice = Dice()

        btnRollDice.setOnClickListener{
            diceObject.roll()
            tvShowDiceNum.text = "The dice is rolled to : ${diceObject.getNum()}"
            imgDice.setImageResource(Dice.imageList.get(diceObject.getNum()-1))
        }
    }
}