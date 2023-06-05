package com.neupanesushant.djangowithandroid

import retrofit2.http.GET

interface Endpoints {

    @GET("companies/")
    suspend fun getAllCompanies() : List<Company>
}