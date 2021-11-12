package dev.alimansour.ireddit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.usecase.AddPostToFavoriteUseCase
import dev.alimansour.domain.usecase.DisposeObserversUseCase
import dev.alimansour.domain.usecase.GetPostsUseCase
import dev.alimansour.domain.usecase.SearchForPostUseCase
import dev.alimansour.domain.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val searchForPostUseCase: SearchForPostUseCase,
    private val addPostToFavoriteUseCase: AddPostToFavoriteUseCase,
    private val disposeObserversUseCase: DisposeObserversUseCase
) : ViewModel() {
    private val _posts = MediatorLiveData<Resource<List<Post>>>()
    val posts: LiveData<Resource<List<Post>>>
        get() = _posts

    private val _action = MediatorLiveData<Resource<String>>()
    val action: LiveData<Resource<String>>
        get() = _action

    fun getPosts(limit: Int, after: String) {
        _posts.addSource(getPostsUseCase.execute(limit, after)) { value ->
            _posts.postValue(value)
        }
    }

    fun searchForPost(query: String, limit: Int, after: String) {
        _posts.addSource(searchForPostUseCase.execute(query, limit, after)) { value ->
            _posts.postValue(value)
        }
    }

    fun addPostToFavorite(post: Post) {
        _action.addSource(addPostToFavoriteUseCase.execute(post)) { value ->
            _action.postValue(value)
        }
    }

    override fun onCleared() {
        disposeObserversUseCase.execute()
        super.onCleared()
    }
}

@Singleton
class HomeViewModelFactory @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val searchForPostUseCase: SearchForPostUseCase,
    private val addPostToFavoriteUseCase: AddPostToFavoriteUseCase,
    private val disposeObserversUseCase: DisposeObserversUseCase
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                getPostsUseCase,
                searchForPostUseCase,
                addPostToFavoriteUseCase,
                disposeObserversUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}