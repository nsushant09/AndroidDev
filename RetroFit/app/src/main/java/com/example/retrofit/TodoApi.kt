package com.example.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {

    @GET("todos")
    fun getData() : Call<List<Todo>>

}