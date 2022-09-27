package com.example.retrofit.domain

import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class UserUseCase(
    val userRepository: UserRepository
){

//    val userRepository = UserRepositoryImpl()
//    val userRepository = FakeUserRepoImpl()

//    val userRepository: UserRepository by inject(UserRepository::class.java)
    fun requestUserData(): Observable<List<User>> = userRepository.requestListOfUsersData()

}