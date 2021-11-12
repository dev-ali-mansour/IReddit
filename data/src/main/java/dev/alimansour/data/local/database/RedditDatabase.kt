package dev.alimansour.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.alimansour.data.local.dao.PostsDao
import dev.alimansour.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao
}