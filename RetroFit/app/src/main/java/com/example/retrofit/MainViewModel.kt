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

class MainViewModel : ViewModel(){
    val listOfData = MutableLiveData<List<Todo>>()
    init{
        listOfData.value = listOfNotNull()
        getMyData()
    }
    private fun getMyData(){
        //create an object of retrofitToApi class
        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TodoApi::class.java)

        viewModelScope.launch{
            try{
                listOfData.value = retrofit.getData()
                Log.i("MainViewModel", "Response Coroutine is successfull")
            }catch(e : Exception){
                listOfData.value = listOfNotNull()
                Log.i("MainViewModel", "Response Coroutine is not successfull")
            }
        }

//        retrofitData.enqueue(object : Callback<List<Todo>?> {
//            override fun onResponse(call: Call<List<Todo>?>, response: Response<List<Todo>?>) {
//                if(response.body()!=null){
//                    val responseList  = response.body()!!
//                    val listFromResponse = responseList as List<Todo>
//                    Log.i("MainViewModel", "Response is saved to list")
//                    listOfData.value = listFromResponse
//                }
//            }
//
//            override fun onFailure(call: Call<List<Todo>?>, t: Throwable) {
//                Log.i("MainViewModel", "Failed to get response")
//            }
//        })
    }

}