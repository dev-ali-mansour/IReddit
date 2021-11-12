package dev.alimansour.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.alimansour.data.remote.response.PostsResponse
import dev.alimansour.data.remote.response.RedditService
import dev.alimansour.domain.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

class RemoteDataSourceImpl(
    private val redditService: RedditService,
    private val compositeDisposable: CompositeDisposable
) : RemoteDataSource {
    override fun getPosts(limit: Int, after: String): LiveData<List<Post>> {
        val posts = MutableLiveData<List<Post>>()
        compositeDisposable.add(redditService.getPosts(limit = limit, after = after)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<PostsResponse>>() {
                override fun onComplete() {}

                override fun onNext(t: Response<PostsResponse>) {
                    if (t.isSuccessful) {
                        t.body()?.let { response ->
                            posts.postValue(response.toModel())
                        }
                    } else Timber.e("Failed to get Posts: ${t.errorBody()}")
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to get Posts: ${e.message}")
                }
            }
            )
        )
        return posts
    }

    override fun searchForPost(
        query: String,
        limit: Int,
        after: String
    ): LiveData<List<Post>> {
        val posts = MutableLiveData<List<Post>>()
        compositeDisposable.add(redditService.searchForPost(
            query = query,
            limit = limit,
            after = after
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<PostsResponse>>() {
                override fun onComplete() {}

                override fun onNext(t: Response<PostsResponse>) {
                    if (t.isSuccessful) {
                        t.body()?.let { response ->
                            posts.postValue(response.toModel())
                        }
                    } else Timber.e("Failed to get Posts: ${t.errorBody()}")
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to get Posts: ${e.message}")
                }
            }
            )
        )
        return posts
    }

    override fun clearObservers() {
        compositeDisposable.clear()
    }
}