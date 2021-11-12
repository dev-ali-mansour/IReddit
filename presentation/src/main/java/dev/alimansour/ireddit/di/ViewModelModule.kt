package dev.alimansour.ireddit.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dev.alimansour.ireddit.ui.home.HomeViewModel
import dev.alimansour.ireddit.ui.home.HomeViewModelFactory

@Module
object ViewModelModule {

    @Provides
    fun provideHomeViewModel(
        activity: FragmentActivity,
        homeViewModelFactory: HomeViewModelFactory
    ): HomeViewModel {
        return ViewModelProvider(activity, homeViewModelFactory)[HomeViewModel::class.java]
    }
}