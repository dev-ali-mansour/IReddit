package dev.alimansour.domain.repository

import dev.alimansour.domain.model.Post

interface PostsRepository {
    fun getPosts(): List<Post>
    fun addToFavorites(post: Post)
    fun removeFromFavorites(post: Post)
    fun clearFavorites()
    fun search(query: String)
}