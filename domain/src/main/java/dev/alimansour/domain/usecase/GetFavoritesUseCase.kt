package dev.alimansour.domain.usecase

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository
import dev.alimansour.domain.util.Resource
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repository: PostsRepository) {

    fun execute(): LiveData<Resource<List<Post>>> = repository.getFavorites()
}