package com.post.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.post.data.local.entity.PostDeletedEntity
import com.post.data.local.entity.PostEntity
import com.post.domain.models.Post

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: List<PostEntity>)

    @Query("SELECT * FROM posts")
    suspend fun getPosts(): List<PostEntity>

    @Query("DELETE FROM posts WHERE author= :author AND creationDate= :date AND url =:url AND description=:description")
    suspend fun deleteEntity(author: String, date: String, url: String, description: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeletedPost(post: PostDeletedEntity)

    @Query("SELECT * FROM posts_deleted")
    suspend fun getDeletedPost(): List<PostDeletedEntity>


}