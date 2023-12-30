package com.neupanesushant.imstask.data.repo

import com.neupanesushant.imstask.data.model.Employee
import com.neupanesushant.imstask.data.model.IndiEmployee
import com.neupanesushant.imstask.data.model.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmployeeEndpoints {
    @GET("employees")
    suspend fun getEmployees(): Response<List<Employee>>

    @POST("create")
    suspend fun addEmployee(@Body employee: IndiEmployee): Response<IndiEmployee>

    @PUT("update/{id}")
    suspend fun updateEmployee(
        @Path("id") id: Int,
        @Body employee: IndiEmployee
    ): Response<IndiEmployee>

    @DELETE("delete/{id}")
    suspend fun deleteEmployee(@Path("id") id: Int): Response<Any>
}