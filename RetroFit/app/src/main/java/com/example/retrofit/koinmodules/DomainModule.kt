package com.example.retrofit.koinmodules

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.retrofit.Constants
import com.example.retrofit.EndPoints
import com.example.retrofit.data.*
import com.example.retrofit.domain.*
import com.example.retrofit.router.RouteProvider
import org.koin.dsl.module

fun domainModule()= module{

    single{
        RouteProvider(Constants.BASE_URL)
    }

//    single<TodoRepository> {
//        TodoRepositoryImpl(get<RouteProvider>(), get<EndPoints>())
//    }
//
//    single{
//        TodoUseCase(get(), get(),get())
//    }
//
//    single<UserRepository> {
//        UserRepositoryImpl(get<RouteProvider>(), get<EndPoints>())
//    }
//
//    single{
//        UserUseCase(get())
//    }
//
//
//    single<PostRepository>{
//        PostRepositoryImpl(get<RouteProvider>(), get<EndPoints>())
//    }
//
//    single{
//        PostUseCase(get())
//    }

    single <TodoRepository>{ TodoRepositoryImpl() }
    single { TodoUseCase() }
    single{ TodoRepositoryImpl() }
    single { UserRepositoryImpl() }
    single{PostRepositoryImpl()}
    single <UserRepository>{ UserRepositoryImpl() }
    single{ UserUseCase() }
    single<PostRepository>{PostRepositoryImpl()}
    single { PostUseCase() }

}

fun overrideDomainModule() = module(override = true){

//    single<UserRepository>{
//        FakeUserRepoImpl()
//    }

//    single{
//        UserUseCase(get())
//    }

}