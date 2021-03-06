package dev.alimansour.domain.usecase

import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository
import javax.inject.Inject

class RemovePostFromFavoriteUseCase @Inject constructor(private val repository: PostsRepository) {

    fun execute(post: Post) = repository.removeFromFavorites(post)
}