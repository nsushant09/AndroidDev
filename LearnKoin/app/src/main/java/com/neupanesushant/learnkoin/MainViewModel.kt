package com.neupanesushant.learnkoin

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val repository : MainRepository
) : ViewModel() {



    fun doNetworkCall(){
        Log.i("MainViewModel", "Something is printed here")
    }
}