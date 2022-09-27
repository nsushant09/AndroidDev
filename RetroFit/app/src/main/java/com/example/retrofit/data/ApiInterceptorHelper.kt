package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import org.koin.java.KoinJavaComponent.inject

class ApiInterceptorHelper<T : Any>() {
    val endPoints: EndPoints by inject(EndPoints::class.java)
    val gsonBuilder: Gson by inject(Gson::class.java)


    fun requestData(url: String, clazz: Class<T>): Observable<T> {
        return endPoints.getData(url)
            .map {

                return@map gsonBuilder.fromJson(gsonBuilder.toJson(it), clazz)

            }
    }

    fun requestUsersData(url : String, clazz : Class<T>) : Observable<T>{
        return endPoints.getUsersData(url)
            .map {
                return@map gsonBuilder.fromJson(gsonBuilder.toJson(it), clazz)
            }
    }


}
