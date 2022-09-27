package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.User
import com.example.retrofit.domain.UserRepository
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class UserRepositoryImpl(
    val routeProvider: RouteProvider, val endPoints: EndPoints
) : UserRepository {

//    val routeProvider: RouteProvider by inject(RouteProvider::class.java)
    val apiInterceptorHelper = ApiInterceptorHelper<Array<User>>()

    override fun requestListOfUsersData(): Observable<List<User>> {
        val arrayUsers = routeProvider.getUrl(RouteCodeConfig.USERS)
            .take(1)
            .flatMap {
                apiInterceptorHelper.requestUsersData(it.getUrl(), Array<User>::class.java)
            }

        return arrayUsers.flatMap {
            Observable.just(it.toList())
        }
    }

}