package com.example.retrofit.data

import android.util.Log
import com.example.retrofit.EndPoints
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.TodoRepository
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable
import org.koin.java.KoinJavaComponent.inject
import java.lang.reflect.Type

class TodoRepositoryImpl(
//    val routeProvider : RouteProvider, val endPoints : EndPoints
    ) : TodoRepository {

    val routeProvider : RouteProvider by inject(RouteProvider::class.java)
    val apiInterceptorHelper = ApiInterceptorHelper<Array<Todo>>()

//    val endPoints : EndPoints by inject(EndPoints::class.java)

    override fun requestData(): Observable<List<Todo>> {

        val array = routeProvider.getUrl(RouteCodeConfig.TODOS)
            .take(1)
            .flatMap {

                var finalList = mutableListOf<Todo>()
                apiInterceptorHelper.requestData(it.getUrl(),Array<Todo>::class.java)

            }

        return array.flatMap {
            val list = it.toList()
            Observable.just(list)
        }
    }

}