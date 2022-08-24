package com.example.retrofit

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(
    val todoRetrofitService : TodoApi
) : ViewModel(){
    val listOfData = MutableLiveData<List<Todo>>()
    init{
        listOfData.value = listOfNotNull()
        getMyData()
    }
    private fun getMyData(){
        viewModelScope.launch{
            try{
                listOfData.value = todoRetrofitService.getData()
                Log.i("MainViewModel", "Response Coroutine is successfull")
            }catch(e : Exception){
                listOfData.value = listOfNotNull()
                Log.i("MainViewModel", "Response Coroutine is not successfull")
            }
        }
    }

}