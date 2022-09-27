package com.example.retrofit.data

import com.example.retrofit.domain.User
import com.example.retrofit.domain.UserRepository
import io.reactivex.rxjava3.core.Observable

class FakeUserRepoImpl : UserRepository {
    override fun requestListOfUsersData(): Observable<List<User>> {
        val listOfUser = ArrayList<User>()
        listOfUser.apply {

            add(User(1, "Sushant Neupane", "nsushant09@gmail.com"))
            add(User(2, "Yogesh Bhatta", "byogesh10@gmail.com"))
            add(User(10, "Utsab Sapkota", "sutsab10@gmail.com"))
            add(User(12, "Pratyush Adhikari", "apratyush12@gmail.com"))
            add(User(82, "Suprit Gautam", "gsuprit88@gmail.com"))
            add(User(99, "Aayush Gautam", "gaayush99@gmail.com"))

        }

        return Observable.just(listOfUser.toList())
    }
}