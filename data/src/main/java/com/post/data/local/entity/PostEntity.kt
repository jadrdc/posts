package com.post.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    val description: String,
    val creationDate: String,
    val author: String,
    val url:String
)
