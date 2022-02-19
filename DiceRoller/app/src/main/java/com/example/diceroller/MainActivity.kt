package com.example.diceroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diceObject : Dice = Dice()
        binding.btnRollDice.setOnClickListener{
            diceRolled(diceObject)
        }
    }

    @SuppressLint("SetTextI18n")
    fun diceRolled(diceObject : Dice){
        diceObject.roll()
        binding.tvShowDiceNum.text = "The dice is rolled to : ${diceObject.getNum()}"
        binding.imgDice.setImageResource(Dice.imageList.get(diceObject.getNum()-1))

        if(diceObject.getNum() == diceObject.getLuckyNum()){
            binding.layoutMainActivity.setBackgroundColor(resources.getColor(R.color.gold))
            binding.tvWin.text = "Congratulations !! You Win"
            binding.tvChange.text = "The lucky number is randomly selected again"
            diceObject.changeLucky()
        }else{
            binding.layoutMainActivity.setBackgroundColor(resources.getColor(R.color.silver))
            binding.tvWin.text = ""
            binding.tvChange.text = ""
        }
    }
}