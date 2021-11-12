package dev.alimansour.ireddit.ui.home

import androidx.lifecycle.*
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.usecase.GetPostsUseCase
import dev.alimansour.domain.usecase.SearchForPostUseCase
import dev.alimansour.domain.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val searchForPostUseCase: SearchForPostUseCase
) : ViewModel() {
    private val _posts = MediatorLiveData<Resource<List<Post>>>()
    val posts: LiveData<Resource<List<Post>>>
        get() = _posts

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

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
}

@Singleton
class HomeViewModelFactory @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val searchForPostUseCase: SearchForPostUseCase
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                getPostsUseCase,
                searchForPostUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}