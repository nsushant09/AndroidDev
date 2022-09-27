package com.example.retrofit.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostUseCase(
    val postRepository: PostRepository
) : KoinComponent {

//    val postRepository: PostRepository by inject()

    fun setPostData(post: Posts) = postRepository.setPostData(post)

}