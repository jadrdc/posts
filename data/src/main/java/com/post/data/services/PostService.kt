package com.post.data.services

import com.post.data.entities.PostListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("search_by_date?query=mobile")
    suspend fun getPostList(
        @Query("page") page: Int = 1
    ): Response<PostListResponse>
}