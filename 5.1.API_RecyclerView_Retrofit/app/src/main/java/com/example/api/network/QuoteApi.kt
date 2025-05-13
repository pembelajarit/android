package com.example.api.network

import com.example.api.response.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {
    @GET("quotes")
    fun getQuotes(): Call<QuoteResponse>
}