package com.neupanesushant.spotifyapiwork

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsAPI {
    @GET("qotd/")
    fun getData() : Call<QuoteDate>
}