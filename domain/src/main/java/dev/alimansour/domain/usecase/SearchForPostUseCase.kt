package dev.alimansour.domain.usecase

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository

class SearchForPostUseCase(private val repository: PostsRepository) {

    fun execute(query: String): LiveData<List<Post>> = repository.search(query)
}