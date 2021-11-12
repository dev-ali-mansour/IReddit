package dev.alimansour.data.local

import dev.alimansour.data.local.dao.PostsDao
import dev.alimansour.data.local.entity.PostEntity
import io.reactivex.Completable
import io.reactivex.Observable

class LocalDataSourceImpl(private val postsDao: PostsDao) : LocalDataSource {
    override fun addToFavorites(post: PostEntity): Completable =
        postsDao.insert(post)

    override fun removeFromFavorites(post: PostEntity): Completable =
        postsDao.delete(post)

    override fun clearFavorites(): Completable = postsDao.clearPosts()

    override fun getFavorites(): Observable<List<PostEntity>> = postsDao.getPosts()
}