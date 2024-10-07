package com.post.domain.interfaces

import com.post.core.util.OperationResult
import com.post.domain.models.Post

interface PostRepository {
    suspend fun getPosts(): OperationResult<List<Post>>
    suspend fun delete(post: Post): OperationResult<Boolean>
    suspend fun getOfflinePosts(): OperationResult<List<Post>>
}