package dev.alimansour.data.remote

import dev.alimansour.data.remote.response.PostsResponse
import dev.alimansour.data.remote.response.RedditService
import io.reactivex.Observable
import retrofit2.Response

class RemoteDataSourceImpl(private val redditService: RedditService) : RemoteDataSource {
    override fun getPosts(limit: Int, after: String): Observable<Response<PostsResponse>> {
        TODO("Not yet implemented")
    }

    override fun searchForPost(
        query: String,
        limit: Int,
        after: String
    ): Observable<Response<PostsResponse>> {
        TODO("Not yet implemented")
    }
}