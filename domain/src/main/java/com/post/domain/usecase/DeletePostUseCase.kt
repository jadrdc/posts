package com.post.domain.usecase

import com.post.core.util.OperationResult
import com.post.domain.interfaces.PostRepository
import com.post.domain.models.Post

class DeletePostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(post: Post): OperationResult<Boolean> {
        return repository.delete(post)
    }
}
