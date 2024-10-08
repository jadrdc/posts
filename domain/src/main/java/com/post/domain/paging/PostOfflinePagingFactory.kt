package com.post.domain.paging

import androidx.paging.PagingSource
import com.post.domain.interfaces.PostLocalDataSource
import com.post.domain.interfaces.PostRemoteDataSource
import com.post.domain.models.Post

class PostOfflinePagingSourceFactory(
    private val dataSource: PostLocalDataSource
) {
    fun getPagingSource(): PagingSource<Int, Post> {
        return PostsOfflinePagingSource(
            dataSource
        )
    }
}