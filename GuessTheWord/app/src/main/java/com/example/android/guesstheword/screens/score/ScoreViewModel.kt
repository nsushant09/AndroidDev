package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore : Int) : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _playAgain = MutableLiveData<Boolean>()
    val playAgain : LiveData<Boolean> get() = _playAgain
    init{
        _score.value = finalScore
        _playAgain.value = false
        Log.i("ScoreViewModel", "The final score is : $finalScore")
    }

    fun onPlayAgainClick(){
        _playAgain.value = true
    }
}