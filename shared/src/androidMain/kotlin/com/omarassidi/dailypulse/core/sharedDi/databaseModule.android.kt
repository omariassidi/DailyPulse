package com.omarassidi.dailypulse.core.sharedDi

import app.cash.sqldelight.db.SqlDriver
import com.omarassidi.dailypulse.core.DatabaseDriverFactory
import com.omarassidi.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module
    get() = module {
        factory<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
        factory<DailyPulseDatabase> { DailyPulseDatabase(get()) }
    }