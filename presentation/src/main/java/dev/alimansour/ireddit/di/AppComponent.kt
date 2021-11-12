package dev.alimansour.ireddit.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.alimansour.ireddit.MainActivity
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

}

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppContext(
    val value: String = "AppContext"
)