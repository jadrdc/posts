package com.post.domain.paging

import androidx.paging.PagingSource
import com.post.domain.interfaces.PostRemoteDataSource
import com.post.domain.models.Post
class PostPagingSourceFactory(
    private val dataSource: PostRemoteDataSource
) {
    fun getPagingSource(): PagingSource<Int, Post> {
        return PostsPagingSource(
            dataSource
        )
    }
}