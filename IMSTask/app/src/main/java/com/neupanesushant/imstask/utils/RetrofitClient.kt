package com.neupanesushant.imstask.utils

import com.neupanesushant.imstask.data.repo.EmployeeEndpoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: Retrofit
        get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .build()

    val employeeEndpoints: EmployeeEndpoints = instance.create(EmployeeEndpoints::class.java)
}