package com.example.diceroller

import kotlin.properties.Delegates
import kotlin.random.Random

class Dice() {
    private var number : Int = 1
    private var luckyNumber : Int = Random.nextInt(1,7)

    companion object{
        val imageList : Array<Int> = arrayOf(R.drawable.dice, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6)
    }
    fun roll(){
        number = Random.nextInt(1,7)
    }
    fun getNum() : Int = number
    fun getLuckyNum() : Int = luckyNumber
    fun changeLucky(){
        luckyNumber = Random.nextInt(1,7)
    }
}