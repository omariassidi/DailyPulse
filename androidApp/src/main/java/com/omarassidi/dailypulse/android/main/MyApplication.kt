package com.omarassidi.dailypulse.android.main

import android.app.Application
import com.omarassidi.dailypulse.core.sharedDi.databaseModule
import com.omarassidi.dailypulse.core.sharedDi.sharedModules
import com.omarassidi.dailypulse.core.sharedDi.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(sharedModules + viewModelsModule + databaseModule)
        }
    }
}