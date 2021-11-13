package dev.alimansour.ireddit.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Subcomponent
import dev.alimansour.ireddit.ui.MainActivity
import dev.alimansour.ireddit.ui.favorites.FavoritesFragment
import dev.alimansour.ireddit.ui.home.HomeFragment
import javax.inject.Scope

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun context(@AppContext context: Context): Builder

        @BindsInstance
        fun activity(activity: FragmentActivity): Builder

        fun build(): ViewModelComponent
    }

    // Classes that can be injected by this Component
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(favoritesFragment: FavoritesFragment)
}

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ActivityScope