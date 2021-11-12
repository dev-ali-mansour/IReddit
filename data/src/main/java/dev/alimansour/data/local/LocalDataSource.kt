package dev.alimansour.data.local

import dev.alimansour.data.local.entity.PostEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {

    fun addToFavorites(post: PostEntity): Completable

    fun removeFromFavorites(post: PostEntity): Completable

    fun clearFavorites(): Completable

    fun getFavorites(): Observable<List<PostEntity>>
}