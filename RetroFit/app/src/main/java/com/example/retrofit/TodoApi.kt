package com.example.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TodoApi {

    @GET("todos")
    suspend fun getData() : List<Todo>

}

object TodoApiService{
    val retrofitService : TodoApi by lazy { retrofit.create(TodoApi::class.java)}
}