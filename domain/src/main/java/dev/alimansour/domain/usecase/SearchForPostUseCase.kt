package dev.alimansour.domain.usecase

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository
import dev.alimansour.domain.util.Resource
import javax.inject.Inject

class SearchForPostUseCase @Inject constructor(private val repository: PostsRepository) {

    fun execute(query: String, limit: Int, after: String): LiveData<Resource<List<Post>>> =
        repository.searchForPost(query, limit, after)
}