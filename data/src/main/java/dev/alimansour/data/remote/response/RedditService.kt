package dev.alimansour.data.remote.response

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("top.json")
    fun getPosts(
        @Query("t") t: String = "all",
        @Query("limit") limit: Int,
        @Query("after") after: String,
    ): Observable<Response<PostsResponse>>

    @GET("search.json")
    fun searchForPost(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("after") after: String,
    ): Observable<Response<PostsResponse>>
}