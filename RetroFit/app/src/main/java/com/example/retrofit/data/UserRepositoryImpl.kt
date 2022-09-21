package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.User
import com.example.retrofit.domain.UserRepository
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable

class UserRepositoryImpl(val routeProvider: RouteProvider, val endPoints: EndPoints) : UserRepository{

    override fun requestListOfUsersData(): Observable<List<User>> {
        return routeProvider.getUrl(RouteCodeConfig.USERS)
            .take(1)
            .flatMap { route ->
                endPoints.getUsersData(route.getUrl())
            }
    }

}