package com.omarassidi.dailypulse.core.network


sealed class AppFailure : Throwable() {
    object Network : AppFailure()
    object Unauthorized : AppFailure()
    data class Api(val body: Any) : AppFailure()
    object Unknown : AppFailure()
}
