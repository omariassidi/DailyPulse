package com.omarassidi.dailypulse.core.sharedDi

import app.cash.sqldelight.db.SqlDriver
import com.omarassidi.dailypulse.core.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module
import com.omarassidi.dailypulse.db.DailyPulseDatabase


actual val databaseModule: Module
    get() = module {
        factory<SqlDriver> { DatabaseDriverFactory().createDriver() }
        factory { DailyPulseDatabase(get()) }
    }