package dev.alimansour.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.alimansour.data.local.LocalDataSource
import dev.alimansour.data.remote.RemoteDataSource
import dev.alimansour.data.remote.response.PostsResponse
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

class PostsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val compositeDisposable: CompositeDisposable
) : dev.alimansour.domain.repository.PostsRepository {
    override fun getPosts(limit: Int, after: String): LiveData<Resource<List<Post>>> {
        val resource = MutableLiveData<Resource<List<Post>>>()
        resource.value = Resource.Loading()
        compositeDisposable.add(remoteDataSource.getPosts(limit = limit, after = after)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<PostsResponse>>() {
                override fun onComplete() {}

                override fun onNext(t: Response<PostsResponse>) {
                    if (t.isSuccessful) {
                        t.body()?.let { response ->
                            resource.postValue(Resource.Success(response.toModel()))
                        }
                    } else Timber.e("Failed to get Posts: ${t.errorBody()}")
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to get Posts: ${e.message}")
                    resource.postValue(Resource.Error(e.message.toString()))
                }
            }
            )
        )
        return resource
    }

    override fun searchForPost(query: String, limit: Int, after: String): LiveData<Resource<List<Post>>> {
        val resource = MutableLiveData<Resource<List<Post>>>()
        resource.value = Resource.Loading()
        compositeDisposable.add(remoteDataSource.searchForPost(
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
                            resource.postValue(Resource.Success(response.toModel()))
                        }
                    } else Timber.e("Failed to get Posts: ${t.errorBody()}")
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to get Posts: ${e.message}")
                    resource.postValue(Resource.Error(e.message.toString()))
                }
            }
            )
        )
        return resource
    }

    override fun addToFavorites(post: Post) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorites(post: Post) {
        TODO("Not yet implemented")
    }

    override fun clearFavorites() {
        TODO("Not yet implemented")
    }
}