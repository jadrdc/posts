package com.post.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    var description: String,
    var creationDate: String,
    var author: String
)
