package com.omarassidi.dailypulse.core

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.omarassidi.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = DailyPulseDatabase.Schema,
        context = context,
        name = "DailyPulse.Database.db"
    )
}