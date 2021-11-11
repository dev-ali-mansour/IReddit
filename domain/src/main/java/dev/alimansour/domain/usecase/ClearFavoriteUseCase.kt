package dev.alimansour.domain.usecase

import dev.alimansour.domain.repository.PostsRepository

class ClearFavoriteUseCase(private val repository: PostsRepository) {

    fun execute() = repository.clearFavorites()
}