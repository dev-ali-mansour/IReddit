package dev.alimansour.data.local

import dev.alimansour.data.local.entity.PostEntity
import io.reactivex.Observable

interface LocalDataSource {

    fun addToFavorites(post: PostEntity)

    fun removeFromFavorites(post: PostEntity)

    fun clearFavorites()

    fun getFavorites(): Observable<List<PostEntity>>
}