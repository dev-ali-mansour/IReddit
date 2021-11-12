package dev.alimansour.domain.repository

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.util.Resource

interface PostsRepository {
    fun getPosts(limit: Int, after: String): LiveData<Resource<List<Post>>>
    fun searchForPost(query: String, limit: Int, after: String): LiveData<Resource<List<Post>>>
    fun addToFavorites(post: Post)
    fun removeFromFavorites(post: Post)
    fun clearFavorites()
}