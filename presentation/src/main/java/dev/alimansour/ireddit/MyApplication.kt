package dev.alimansour.ireddit

import androidx.multidex.MultiDexApplication
import dev.alimansour.ireddit.di.AppComponent
import dev.alimansour.ireddit.di.DaggerAppComponent
import timber.log.Timber

class MyApplication : MultiDexApplication() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(applicationContext).build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}