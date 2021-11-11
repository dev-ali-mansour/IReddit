package dev.alimansour.domain.usecase

import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository

class RemovePostFromFavoriteUseCase(private val repository: PostsRepository) {

    fun execute(post: Post) = repository.removeFromFavorites(post)
}