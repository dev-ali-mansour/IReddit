package dev.alimansour.ireddit.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.alimansour.ireddit.MainActivity
import dev.alimansour.ireddit.ui.favorites.FavoritesFragment
import dev.alimansour.ireddit.ui.home.HomeFragment
import javax.inject.Qualifier
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RoomModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@AppContext context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(favoritesFragment: FavoritesFragment)

}

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppContext(
    val value: String = "AppContext"
)