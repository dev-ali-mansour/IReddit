package dev.alimansour.domain.usecase

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository

class GetPostsUseCase(private val repository: PostsRepository) {

    fun execute(): LiveData<List<Post>> = repository.getPosts()
}