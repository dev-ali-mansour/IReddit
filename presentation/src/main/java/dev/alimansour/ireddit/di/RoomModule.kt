package dev.alimansour.ireddit.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.alimansour.data.local.dao.PostsDao
import dev.alimansour.data.local.database.RedditDatabase
import javax.inject.Singleton

@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideRedditDatabase(@AppContext context: Context): RedditDatabase {
        return Room.databaseBuilder(
            context,
            RedditDatabase::class.java,
            "reddit.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDao(db: RedditDatabase): PostsDao {
        return db.postDao()
    }

}