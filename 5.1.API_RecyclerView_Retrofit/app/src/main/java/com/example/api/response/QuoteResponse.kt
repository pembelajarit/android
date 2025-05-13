package com.example.api.response

import com.example.api.model.Quote

data class QuoteResponse(
    val quotes: List<Quote>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
