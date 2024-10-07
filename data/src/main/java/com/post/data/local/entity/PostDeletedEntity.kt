package com.post.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_deleted")
data class PostDeletedEntity(
    @PrimaryKey val id: Int,
    val description: String,
    val creationDate: String,
    val author: String,
    val url:String
)
