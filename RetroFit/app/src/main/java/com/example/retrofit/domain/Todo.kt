package com.example.retrofit.domain

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)