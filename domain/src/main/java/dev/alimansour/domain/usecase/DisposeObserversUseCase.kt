package dev.alimansour.domain.usecase

import dev.alimansour.domain.repository.PostsRepository
import javax.inject.Inject

class DisposeObserversUseCase @Inject constructor(private val repository: PostsRepository) {

    fun execute() = repository.disposeObservers()
}