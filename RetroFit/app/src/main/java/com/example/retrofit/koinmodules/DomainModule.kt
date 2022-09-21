package com.example.retrofit.koinmodules

import com.example.retrofit.Constants
import com.example.retrofit.EndPoints
import com.example.retrofit.data.TodoRepositoryImpl
import com.example.retrofit.data.UserRepositoryImpl
import com.example.retrofit.domain.TodoRepository
import com.example.retrofit.domain.TodoUseCase
import com.example.retrofit.domain.UserRepository
import com.example.retrofit.domain.UserUseCase
import com.example.retrofit.router.RouteProvider
import org.koin.dsl.module

fun domainModule()= module{

    single{
        RouteProvider(Constants.BASE_URL)
    }

    single<TodoRepository> {
        TodoRepositoryImpl(get<RouteProvider>(), get<EndPoints>())
    }

    single{
        TodoUseCase(get(), get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get<RouteProvider>(), get<EndPoints>())
    }

    single{
        UserUseCase(get())
    }

}