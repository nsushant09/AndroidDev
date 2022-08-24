package com.neupanesushant.learnkoin

class MainRepositoryImpl(
    private val api : MyApi
) : MainRepository {
    override fun doNetworkCall() {
        api.callApi()
    }
}