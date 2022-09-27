package com.example.retrofit.data

import com.example.retrofit.Constants
import com.example.retrofit.EndPoints
import com.example.retrofit.domain.ApiPostResponse
import com.example.retrofit.domain.PostRepository
import com.example.retrofit.domain.Posts
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PostRepositoryImpl(
//    val routeProvider : RouteProvider, val endPoints: EndPoints
    ) : PostRepository , KoinComponent{

    val routeProvider : RouteProvider by inject()
    val endPoints : EndPoints by inject()

    override fun setPostData(post : Posts): Observable<ApiPostResponse> {
        return routeProvider.getUrl(RouteCodeConfig.POSTS)
            .take(1)
            .flatMap { route ->
                endPoints.setPostData(route.getUrl(), post)
            }
    }
}