package com.example.retrofit.domain

import java.io.Serializable

data class Todo(
    val completed: Boolean? = null,
    val id: Int? = null,
    val title: String? = null,
    val userId: Int? = null
)