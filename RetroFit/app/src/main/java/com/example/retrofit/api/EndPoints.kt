package com.example.retrofit

import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

//private val retrofit = Retrofit
//    .Builder()
//    .addConverterFactory(GsonConverterFactory.create())
//    .baseUrl(BASE_URL)
//    .build()

interface EndPoints {

    @GET
    fun getData(@Url url : String) : Observable<List<Todo>>

    @GET
    fun getUsersData(@Url url : String) : Observable<List<User>>

}
