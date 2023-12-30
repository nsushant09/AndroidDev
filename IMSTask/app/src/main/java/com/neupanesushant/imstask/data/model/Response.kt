package com.neupanesushant.imstask.data.model

data class Response<T>(
    val data: T,
    val message: String,
    val status: String
)