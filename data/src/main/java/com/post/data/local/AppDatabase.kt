package com.post.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.post.data.local.dao.PostsDao
import com.post.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDAO(): PostsDao
}