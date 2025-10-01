package com.omarassidi.dailypulse.android.main

import android.app.Application
import com.omarassidi.dailypulse.android.features.viewModelModule
import com.omarassidi.dailypulse.core.sharedDi.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(sharedModules + viewModelModule)
        }
    }
}