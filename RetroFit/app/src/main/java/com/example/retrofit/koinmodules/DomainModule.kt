package com.example.retrofit.koinmodules

import com.example.retrofit.Constants
import com.example.retrofit.data.PostRepositoryImpl
import com.example.retrofit.data.TodoRepositoryImpl
import com.example.retrofit.data.UserRepositoryImpl
import com.example.retrofit.domain.*
import com.example.retrofit.router.RouteProvider
import org.koin.dsl.module

fun domainModule() = module {

    single {
        RouteProvider(Constants.BASE_URL)
    }

    single<TodoRepository> { TodoRepositoryImpl(get(), get()) }
    single { TodoUseCase(get(), get(), get()) }
//    single { TodoRepositoryImpl(get(), get()) }
//    single { UserRepositoryImpl(get(), get()) }
//    single { PostRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single { UserUseCase(get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
    single { PostUseCase(get()) }

}

fun overrideDomainModule() = module(override = true) {

//    single<UserRepository>{
//        FakeUserRepoImpl()
//    }

//    single{
//        UserUseCase(get())
//    }

}