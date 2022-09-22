package com.example.retrofit.domain

class PostUseCase(val postRepository: PostRepository) {

    fun setPostData(post : Posts) = postRepository.setPostData(post)

}