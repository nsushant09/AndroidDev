package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.example.retrofit.domain.ApiPostResponse
import com.example.retrofit.domain.PostRepository
import com.example.retrofit.domain.Posts
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostRepositoryImpl(
    val routeProvider : RouteProvider, val endPoints: EndPoints
) : PostRepository{

//    val routeProvider: RouteProvider by inject()
//    val endPoints: EndPoints by inject()

    override fun setPostData(post: Posts): Observable<ApiPostResponse> {
        return routeProvider.getUrl(RouteCodeConfig.POSTS)
            .take(1)
            .flatMap { route ->
                endPoints.setPostData(route.getUrl(), post)
            }
    }
}