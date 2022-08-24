package com.neupanesushant.learnkoin

import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    //factory will create a new instance every time a new instance is requested but single will uses the singleton
    //single is used to create singleton
    single() {
        Retrofit.Builder()
            .baseUrl("https://google.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MyApi::class.java)

    }

    single(qualifier = named("retrofitInstance")){
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<MainRepository> {
        //provide retrofit instance from above using getfunction
        // it will automatically find and uses it
        MainRepositoryImpl(get())
    }

    //used this instead of viewmodel factory
    viewModel{
        MainViewModel(get())
    }
}