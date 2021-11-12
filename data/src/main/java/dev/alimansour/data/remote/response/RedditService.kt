package dev.alimansour.data.remote.response

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditService {

    @GET("top.json")
    fun getPosts(
        @Path("t") t: String = "all",
        @Path("limit:") limit: Int,
        @Path("after:") after: String,
    ): Observable<Response<PostsResponse>>

    @GET("search.json")
    fun searchForPost(
        @Path("q") query: String,
        @Path("limit:") limit: Int,
        @Path("after:") after: String,
    ): Observable<Response<PostsResponse>>
}