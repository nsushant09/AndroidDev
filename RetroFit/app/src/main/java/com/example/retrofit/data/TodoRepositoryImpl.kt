package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.TodoRepository
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable

class TodoRepositoryImpl(val routeProvider : RouteProvider, val endpoints : EndPoints) : TodoRepository {
    override fun requestData(): Observable<List<Todo>> {
     return routeProvider.getUrl(RouteCodeConfig.TODOS)
         .take(1)
         .flatMap { route ->
             endpoints.getData(route.getUrl())
         }
    }

}