package com.example.retrofit.domain

data class Todo(
    val completed: Boolean? = null,
    val id: Int? = null,
    val title: String? = null,
    val userId: Int? = null
)