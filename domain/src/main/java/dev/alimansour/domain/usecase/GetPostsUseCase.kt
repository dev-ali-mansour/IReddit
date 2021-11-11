package dev.alimansour.domain.usecase

import dev.alimansour.domain.model.Post
import dev.alimansour.domain.repository.PostsRepository

class GetPostsUseCase(private val repository: PostsRepository) {

    fun execute(): List<Post> = repository.getPosts()
}