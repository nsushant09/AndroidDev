package com.example.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PractiseViewModelProvider(val intValue : Int ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PractiseViewModel::class.java)){
            return PractiseViewModel(intValue) as T
        }
        throw IllegalArgumentException("")
    }


}