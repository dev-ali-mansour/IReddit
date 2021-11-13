package dev.alimansour.data.remote

import dev.alimansour.data.remote.response.PostsResponse
import io.reactivex.Single
import retrofit2.Response

interface RemoteDataSource {

    fun getPosts(limit: Int, after: String): Single<Response<PostsResponse>>

    fun searchForPost(query: String, limit: Int, after: String): Single<Response<PostsResponse>>
}