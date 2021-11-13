package dev.alimansour.data.remote

import dev.alimansour.data.remote.response.PostsResponse
import dev.alimansour.data.remote.response.RedditService
import io.reactivex.Single
import retrofit2.Response

class RemoteDataSourceImpl(
    private val redditService: RedditService
) : RemoteDataSource {

    override fun getPosts(limit: Int, after: String): Single<Response<PostsResponse>> =
        redditService.getPosts(limit = limit, after = after)

    override fun searchForPost(query: String, limit: Int, after: String)
            : Single<Response<PostsResponse>> =
        redditService.searchForPost(query = query, limit = limit, after = after)
}