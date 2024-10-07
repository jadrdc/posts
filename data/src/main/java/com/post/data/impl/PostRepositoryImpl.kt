package com.post.data.impl

import com.post.core.util.OperationResult
import com.post.domain.interfaces.PostLocalDataSource
import com.post.domain.interfaces.PostRemoteDataSource
import com.post.domain.interfaces.PostRepository
import com.post.domain.models.Post

class PostRepositoryImpl(
    private val localDataSource: PostLocalDataSource,
    private val remoteDataSource: PostRemoteDataSource
) : PostRepository {
    override suspend fun getPosts(): OperationResult<List<Post>> {
        return remoteDataSource.getPosts()
    }
}