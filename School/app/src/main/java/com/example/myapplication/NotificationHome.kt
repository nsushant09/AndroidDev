package com.example.myapplication

class NotificationHome constructor(private val title : String, private val description : String) {
    fun getTitle() : String = title
    fun getDesc() : String = description
}