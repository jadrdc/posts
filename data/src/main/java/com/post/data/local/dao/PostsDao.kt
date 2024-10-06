package com.post.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.post.data.local.entity.PostEntity

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: List<PostEntity>)

    @Query("SELECT * FROM posts")
    suspend fun getPosts():List<PostEntity>
}