package com.neupanesushant.rxjava_subject_application.domain

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)