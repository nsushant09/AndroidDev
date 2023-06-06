package com.neupanesushant.djangowithandroid

import retrofit2.Response
import retrofit2.http.*

interface Endpoints {

    @GET("companies/")
    suspend fun getAllCompanies() : List<Company>

    @POST("companies/")
    suspend fun addCompany(@Body company: Company) : Response<Void>

    @DELETE("employee/{id}/")
    suspend fun deleteEmployee(@Path("id") id : Int) : Response<Void>
}