package dev.alimansour.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.alimansour.data.local.LocalDataSource
import dev.alimansour.data.local.entity.PostEntity
import dev.alimansour.data.remote.RemoteDataSource
import dev.alimansour.data.remote.response.PostsResponse
import dev.alimansour.data.util.toEntity
import dev.alimansour.data.util.toModel
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
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

    override fun searchForPost(
        query: String,
        limit: Int,
        after: String
    ): LiveData<Resource<List<Post>>> {
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

    override fun addToFavorites(post: Post): LiveData<Resource<Boolean>> {
        val resource = MutableLiveData<Resource<Boolean>>()
        resource.value = Resource.Loading()
        compositeDisposable.add(localDataSource.addToFavorites(post.toEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    resource.postValue(Resource.Success(true))
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to add post to favorites: ${e.message}")
                    resource.postValue(Resource.Error(e.message.toString()))
                }
            }
            )
        )

        return resource
    }

    override fun removeFromFavorites(post: Post): LiveData<Resource<Boolean>> {
        val resource = MutableLiveData<Resource<Boolean>>()
        resource.value = Resource.Loading()
        compositeDisposable.add(localDataSource.removeFromFavorites(post.toEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    resource.postValue(Resource.Success(true))
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to remove post from favorites: ${e.message}")
                    resource.postValue(Resource.Error(e.message.toString()))
                }
            }
            )
        )

        return resource
    }

    override fun getFavorites(): LiveData<Resource<List<Post>>> {
        val resource = MutableLiveData<Resource<List<Post>>>()
        resource.value = Resource.Loading()
        compositeDisposable.add(localDataSource.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<PostEntity>>() {
                override fun onComplete() {}

                override fun onNext(t: List<PostEntity>) {
                    resource.postValue(Resource.Success(t.map { it.toModel() }))
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

    override fun clearFavorites(): LiveData<Resource<Boolean>> {
        val resource = MutableLiveData<Resource<Boolean>>()
        resource.value = Resource.Loading()
        compositeDisposable.add(localDataSource.clearFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    resource.postValue(Resource.Success(true))
                }

                override fun onError(e: Throwable) {
                    Timber.e("Failed to clear favorites: ${e.message}")
                    resource.postValue(Resource.Error(e.message.toString()))
                }
            }
            )
        )

        return resource
    }

    override fun disposeObservers() {
        compositeDisposable.dispose()
    }
}
