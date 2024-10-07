package com.post.domain.interfaces

import com.post.core.util.OperationResult
import com.post.domain.models.Post

interface PostRemoteDataSource {
    suspend fun getPosts(): OperationResult<List<Post>>
}