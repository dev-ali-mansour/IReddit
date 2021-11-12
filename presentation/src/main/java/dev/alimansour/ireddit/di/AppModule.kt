package dev.alimansour.ireddit.di

import dagger.Module
import dagger.Provides
import dev.alimansour.data.local.LocalDataSource
import dev.alimansour.data.remote.RemoteDataSource
import dev.alimansour.domain.repository.PostsRepository
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(

    ): LocalDataSource {
        TODO("Not implemented yet")
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(

    ): RemoteDataSource {
        TODO("Not implemented yet")
    }

    @Singleton
    @Provides
    fun providePostsRepository(

    ): PostsRepository {
        TODO("Not implemented yet")
    }
}