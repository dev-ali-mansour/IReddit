package dev.alimansour.ireddit.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dev.alimansour.ireddit.ui.favorites.FavoritesViewModel
import dev.alimansour.ireddit.ui.favorites.FavoritesViewModelFactory
import dev.alimansour.ireddit.ui.home.HomeViewModel
import dev.alimansour.ireddit.ui.home.HomeViewModelFactory

@Module
object ViewModelModule {

    @ActivityScope
    @Provides
    fun provideHomeViewModel(
        activity: FragmentActivity,
        homeViewModelFactory: HomeViewModelFactory
    ): HomeViewModel =
        ViewModelProvider(activity, homeViewModelFactory)[HomeViewModel::class.java]

    @ActivityScope
    @Provides
    fun provideFavoritesViewModel(
        activity: FragmentActivity,
        favoritesViewModelFactory: FavoritesViewModelFactory
    ): FavoritesViewModel =
        ViewModelProvider(activity, favoritesViewModelFactory)[FavoritesViewModel::class.java]
}