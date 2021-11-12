package dev.alimansour.data.repository

import dev.alimansour.data.local.LocalDataSource
import dev.alimansour.data.remote.RemoteDataSource
import dev.alimansour.domain.model.Post

class PostsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : dev.alimansour.domain.repository.PostsRepository {
    override fun getPosts(): List<Post> {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(post: Post) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorites(post: Post) {
        TODO("Not yet implemented")
    }

    override fun clearFavorites() {
        TODO("Not yet implemented")
    }

    override fun search(query: String) {
        TODO("Not yet implemented")
    }
}