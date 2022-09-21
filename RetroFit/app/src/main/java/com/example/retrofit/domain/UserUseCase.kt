package com.example.retrofit.domain

import io.reactivex.rxjava3.core.Observable

class UserUseCase(val userRepository: UserRepository) {

    fun requestUserData() : Observable<List<User>> = userRepository.requestListOfUsersData()

}