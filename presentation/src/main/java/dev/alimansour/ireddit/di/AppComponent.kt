package dev.alimansour.ireddit.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
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

    // Types that can be retrieved from the graph
    fun viewModelComponentBuilder(): ViewModelComponent.Builder
}

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppContext(
    val value: String = "AppContext"
)