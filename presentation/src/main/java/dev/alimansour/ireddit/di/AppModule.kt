package dev.alimansour.ireddit.di

import dagger.Module
import dagger.Provides
import dev.alimansour.data.local.LocalDataSource
import dev.alimansour.data.local.LocalDataSourceImpl
import dev.alimansour.data.local.dao.PostsDao
import dev.alimansour.data.remote.RemoteDataSource
import dev.alimansour.data.remote.RemoteDataSourceImpl
import dev.alimansour.data.remote.response.RedditService
import dev.alimansour.data.repository.PostsRepositoryImpl
import dev.alimansour.domain.repository.PostsRepository
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(redditService: RedditService): RemoteDataSource =
        RemoteDataSourceImpl(redditService)

    @Singleton
    @Provides
    fun provideLocalDataSource(postsDao: PostsDao): LocalDataSource =
        LocalDataSourceImpl(postsDao)

    @Singleton
    @Provides
    fun providePostsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): PostsRepository = PostsRepositoryImpl(remoteDataSource, localDataSource)
}