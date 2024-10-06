package com.post.data.mappers

import com.post.data.entities.PostListResponse
import com.post.data.local.entity.PostEntity
import com.post.domain.models.Post

fun PostListResponse.toDomain(): List<Post> {
    return this.hits.mapIndexed { index, model ->
        Post(
            id = index,
            author = model.author,
            description = model.story_title.ifBlank { model.title },
            creationDate = model.created_at
        )
    }
}

fun PostListResponse.toOfflineModel(): List<PostEntity> {
    return this.hits.mapIndexed { index, model ->
        PostEntity(
            id = index,
            author = model.author,
            description = model.story_title.ifBlank { model.title },
            creationDate = model.created_at
        )
    }
}

fun PostEntity.toDomainModel(): Post {
    return Post(
        id = this.id,
        author = this.author,
        description = this.description,
        creationDate = this.creationDate
    )
}
