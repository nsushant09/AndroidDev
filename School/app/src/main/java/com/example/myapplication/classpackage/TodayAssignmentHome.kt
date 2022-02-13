package com.example.myapplication.classpackage

data class TodayAssignmentHome constructor(private val subject : String, private val assignment : String){
    fun getSubject() : String = subject
    fun getAssignment() : String = assignment
}