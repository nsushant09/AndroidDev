package com.example.retrofit.domain

import io.reactivex.rxjava3.core.Observable

interface UserRepository {

    fun requestListOfUsersData(): Observable<List<User>>
}