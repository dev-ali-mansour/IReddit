package dev.alimansour.ireddit

import androidx.multidex.MultiDexApplication
import dev.alimansour.ireddit.di.AppComponent
import dev.alimansour.ireddit.di.DaggerAppComponent

class MyApplication : MultiDexApplication() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(applicationContext).build()
    }
}