package com.example.retrofit.koinmodules

import android.app.Application
import com.example.retrofit.BuildConfig
import com.example.retrofit.EndPoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun netModule(baseUrl: String, application: Application) = module {

    single <Gson>{
        GsonBuilder().create()
    }

    single {
        Dispatcher()
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<EndPoints> {
        get<Retrofit>().create(EndPoints::class.java)
    }
}