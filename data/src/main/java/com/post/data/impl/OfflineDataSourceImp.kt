package com.post.data.impl

import com.post.core.util.OperationResult
import com.post.data.local.dao.PostsDao
import com.post.data.mappers.toDeletedPost
import com.post.data.mappers.toDomain
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

    override suspend fun getPosts(offset: Int): OperationResult<List<Post>> {
        return try {
            val posts = offLineSource.getDeletedPostPaginated(offset)
            OperationResult.Success(posts.map { it.toDomain() })
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun delete(post: Post): OperationResult<Boolean> {
        return try {
            offLineSource.deleteEntity(
                author = post.author,
                description = post.description,
                url = post.url,
                date = post.creationDate
            )
            offLineSource.insertDeletedPost(post.toDeletedPost())
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}