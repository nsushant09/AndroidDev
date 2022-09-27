package com.example.retrofit.domain

import io.reactivex.rxjava3.core.Observable

interface PostRepository {

    fun setPostData(post: Posts): Observable<ApiPostResponse>

}