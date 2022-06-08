package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    // The current word
    private val _word = MutableLiveData<String>()
    val word : LiveData<String> get() = _word
    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score
    // The list of words - the front of the list is the next word to guess

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished : LiveData<Boolean> get() = _eventGameFinished

    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft : LiveData<Long> get()  = _timeLeft

    private lateinit var wordList: MutableList<String>

    private val timer : CountDownTimer

    val currentTimeString = Transformations.map(timeLeft, {time ->
        DateUtils.formatElapsedTime(time/1000)
    })

    init{
        resetList()
        nextWord()
        _score.value = 0
        _eventGameFinished.value = false
        Log.i("GameViewModel", "GameViewModel created")

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                 _timeLeft.value = millisUntilFinished
            }

            override fun onFinish() {
                _eventGameFinished.value = true
            }

        }
        timer.start()

    }

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()


        }
        _word.value = wordList.removeAt(0)

    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    fun onGameFinishComplete(){
        _eventGameFinished.value = false
    }


}