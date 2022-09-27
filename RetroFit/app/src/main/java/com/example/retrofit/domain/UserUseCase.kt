package com.example.retrofit.domain

import com.example.retrofit.data.FakeUserRepoImpl
import com.example.retrofit.data.UserRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

class UserUseCase(
//    val userRepository: UserRepository
    ) : KoinComponent {

//    val userRepository = UserRepositoryImpl()
//    val userRepository = FakeUserRepoImpl()

    val userRepository : UserRepository by inject()
    fun requestUserData() : Observable<List<User>> = userRepository.requestListOfUsersData()

}