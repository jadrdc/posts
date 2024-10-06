package com.post.data.entities

data class ProcessingTimingsMS(
    val _request: Request,
    val afterFetch: AfterFetch,
    val fetch: Fetch,
    val total: Int
)