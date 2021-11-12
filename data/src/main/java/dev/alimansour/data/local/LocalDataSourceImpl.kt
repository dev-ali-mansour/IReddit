package dev.alimansour.data.local

import dev.alimansour.data.local.dao.PostsDao
import dev.alimansour.data.local.entity.PostEntity
import io.reactivex.Observable

class LocalDataSourceImpl(private val postsDao: PostsDao) : LocalDataSource {
    override fun addToFavorites(post: PostEntity) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorites(post: PostEntity) {
        TODO("Not yet implemented")
    }

    override fun clearFavorites() {
        TODO("Not yet implemented")
    }

    override fun getFavorites(): Observable<List<PostEntity>> {
        TODO("Not yet implemented")
    }
}