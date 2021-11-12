package dev.alimansour.domain.repository

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post

interface PostsRepository {
    fun getPosts(): LiveData<List<Post>>
    fun addToFavorites(post: Post)
    fun removeFromFavorites(post: Post)
    fun clearFavorites()
    fun search(query: String):LiveData<List<Post>>
}