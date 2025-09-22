package com.omarassidi.dailypulse.articles.presentation

import com.omarassidi.dailypulse.articles.data.ArticlesMockRepositoryImpl
import com.omarassidi.dailypulse.articles.domain.ArticlesUseCaseImpl
import com.omarassidi.dailypulse.core.domain.ArticlesUseCase
import com.omarassidi.dailypulse.core.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {
    private val _state = MutableStateFlow(ArticlesState(isLoading = true))
    val state: StateFlow<ArticlesState>
        get() = _state.asStateFlow()

    private val useCase: ArticlesUseCase = ArticlesUseCaseImpl(ArticlesMockRepositoryImpl())

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val result = useCase.getArticles()
            if (result.isSuccess) {
                _state.emit(ArticlesState(articles = result.getOrThrow()))
            } else {
                _state.emit(ArticlesState(errorMessage = result.exceptionOrNull()?.message))
            }
        }
    }

}