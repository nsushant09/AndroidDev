package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.example.retrofit.domain.ApiPostResponse
import com.example.retrofit.domain.PostRepository
import com.example.retrofit.domain.Posts
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable

class PostRepositoryImpl(val routeProvider : RouteProvider, val endPoints: EndPoints) : PostRepository {
    override fun setPostData(post : Posts): Observable<ApiPostResponse> {
        return routeProvider.getUrl(RouteCodeConfig.POSTS)
            .take(1)
            .flatMap { route ->
                endPoints.setPostData(route.getUrl(), post)
            }
    }
}