package com.neupanesushant.djangowithandroid

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "http://10.0.2.2:8000/api/v1/"
val appModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<Endpoints> {
        get<Retrofit>().create(Endpoints::class.java)
    }

    single {
        EndpointHelper(get())
    }
}