package dev.alimansour.data.remote

import androidx.lifecycle.LiveData
import dev.alimansour.domain.model.Post

interface RemoteDataSource {

    fun getPosts(limit: Int, after: String): LiveData<List<Post>>

    fun searchForPost(query: String, limit: Int, after: String): LiveData<List<Post>>

    fun clearObservers()
}