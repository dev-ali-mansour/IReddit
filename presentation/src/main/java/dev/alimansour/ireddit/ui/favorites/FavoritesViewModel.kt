package dev.alimansour.ireddit.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.alimansour.domain.model.Post
import dev.alimansour.domain.usecase.ClearFavoriteUseCase
import dev.alimansour.domain.usecase.DisposeObserversUseCase
import dev.alimansour.domain.usecase.GetFavoritesUseCase
import dev.alimansour.domain.usecase.RemovePostFromFavoriteUseCase
import dev.alimansour.domain.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

class FavoritesViewModel(
    private val getFavoriteUseCase: GetFavoritesUseCase,
    private val removePostFromFavoriteUseCase: RemovePostFromFavoriteUseCase,
    private val clearFavoriteUseCase: ClearFavoriteUseCase,
    private val disposeObserversUseCase: DisposeObserversUseCase
) : ViewModel() {
    private val _favorites = MediatorLiveData<Resource<List<Post>>>()
    val favorites: LiveData<Resource<List<Post>>>
        get() = _favorites

    private val _action = MediatorLiveData<Resource<String>>()
    val action: LiveData<Resource<String>>
        get() = _action

    fun getFavorites() {
        _favorites.addSource(getFavoriteUseCase.execute()) { value ->
            _favorites.postValue(value)
        }
    }

    fun removeFromFavorites(post: Post) {
        _action.addSource(removePostFromFavoriteUseCase.execute(post)) { value ->
            _action.postValue(value)
        }
    }

    fun clearFavorites() {
        _action.addSource(clearFavoriteUseCase.execute()) { value ->
            _action.postValue(value)
        }
    }

    override fun onCleared() {
        disposeObserversUseCase.execute()
        super.onCleared()
    }
}

@Singleton
class FavoritesViewModelFactory @Inject constructor(
    private val getFavoriteUseCase: GetFavoritesUseCase,
    private val removePostFromFavoriteUseCase: RemovePostFromFavoriteUseCase,
    private val clearFavoriteUseCase: ClearFavoriteUseCase,
    private val disposeObserversUseCase: DisposeObserversUseCase
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(
                getFavoriteUseCase,
                removePostFromFavoriteUseCase,
                clearFavoriteUseCase,
                disposeObserversUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}