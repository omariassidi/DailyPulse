package com.omarassidi.dailypulse.articles.presentation

import com.omarassidi.dailypulse.articles.data.repository.ArticlesMockRepositoryImpl
import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepositoryImpl
import com.omarassidi.dailypulse.articles.domain.usecase.ArticlesUseCaseImpl
import com.omarassidi.dailypulse.articles.domain.usecase.ArticlesUseCase
import com.omarassidi.dailypulse.core.presentation.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {
    private val _state = MutableStateFlow(ArticlesState(isLoading = true))
    val state: StateFlow<ArticlesState>
        get() = _state.asStateFlow()

    init {
        getArticles()
    }


    fun getArticles(forceRefresh: Boolean = false) {
        scope.launch {
            _state.emit(ArticlesState(isLoading = true, articles = _state.value.articles))
            val result = useCase.getArticles(forceRefresh)
            if (result.isSuccess) {
                _state.emit(ArticlesState(articles = result.getOrThrow()))
            } else {
                _state.emit(
                    ArticlesState(
                        errorMessage = result.exceptionOrNull()?.message ?: "Something went wrong"
                    )
                )
            }
        }
    }

}