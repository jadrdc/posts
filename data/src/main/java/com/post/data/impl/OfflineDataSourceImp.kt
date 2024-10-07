package com.post.data.impl

import com.post.core.util.OperationResult
import com.post.data.local.dao.PostsDao
import com.post.data.mappers.toDomainModel
import com.post.domain.interfaces.PostLocalDataSource
import com.post.domain.models.Post

class OfflineDataSourceImp(
    private val offLineSource: PostsDao
) : PostLocalDataSource {
    override suspend fun getPosts(): OperationResult<List<Post>> {
        return try {
            val posts = offLineSource.getPosts().map {
                it.toDomainModel()
            }
            OperationResult.Success(posts)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}