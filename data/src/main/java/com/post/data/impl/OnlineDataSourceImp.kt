package com.post.data.impl

import com.post.core.util.OperationResult
import com.post.data.local.dao.PostsDao
import com.post.data.mappers.toDomain
import com.post.data.mappers.toOfflineModel
import com.post.data.services.PostService
import com.post.domain.interfaces.PostRemoteDataSource
import com.post.domain.models.Post

class OnlineDataSourceImp(
    private val service: PostService,
    private val offLineSource: PostsDao
) :
    PostRemoteDataSource {
    override suspend fun getPosts(): OperationResult<List<Post>> {
        return try {
            val response = service.getPostList()
            return if (response.isSuccessful) {
                val posts = response.body()
                if (posts != null) {
                    val postEntity = posts.toDomain()
                    offLineSource.insertPosts(posts.toOfflineModel())
                    OperationResult.Success(postEntity)
                } else {
                    OperationResult.Error(Exception("Empty List"))
                }
            } else {
                OperationResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}