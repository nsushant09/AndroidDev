package com.neupanesushant.djangowithandroid

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EndpointHelper(private val endpoints: Endpoints) {

    fun getAllCompanies(): Flow<List<Company>> = flow {
        emit(endpoints.getAllCompanies())
    }

}