package com.post.data.services

import com.post.data.entities.PostListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("search_by_date?query=mobile")
    suspend fun getPostList(): Response<PostListResponse>
}