package com.omarassidi.dailypulse.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {
    actual val scope = CoroutineScope(Dispatchers.IO)

    fun clear() {
        scope.cancel()
    }
}