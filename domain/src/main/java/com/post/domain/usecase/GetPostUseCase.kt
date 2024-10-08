package com.post.domain.usecase

import com.post.core.util.OperationResult
import com.post.domain.interfaces.PostRepository
import com.post.domain.models.Post

class GetPostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(page: Int = 1): OperationResult<List<Post>> {
        return repository.getPosts(page)
    }
}
