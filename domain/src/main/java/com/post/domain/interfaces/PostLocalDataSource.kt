package com.post.domain.interfaces

import com.post.core.util.OperationResult
import com.post.domain.models.Post

interface PostLocalDataSource {
    suspend fun getPosts(): OperationResult<List<Post>>
    suspend fun delete(post: Post): OperationResult<Boolean>
    suspend fun getPosts(offset: Int): OperationResult<List<Post>>

}