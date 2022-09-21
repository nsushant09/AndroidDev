package com.example.retrofit.domain

import io.reactivex.rxjava3.core.Observable

interface TodoRepository {

    fun requestData() : Observable<List<Todo>>

}