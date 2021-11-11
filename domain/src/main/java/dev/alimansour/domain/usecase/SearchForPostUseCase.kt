package dev.alimansour.domain.usecase

import dev.alimansour.domain.repository.PostsRepository

class SearchForPostUseCase(private val repository: PostsRepository) {

    fun execute(query: String) = repository.search(query)
}