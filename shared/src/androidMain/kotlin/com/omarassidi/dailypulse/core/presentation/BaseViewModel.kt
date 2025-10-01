package com.omarassidi.dailypulse.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

actual open class BaseViewModel : ViewModel() {
    actual val scope = viewModelScope
}