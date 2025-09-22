package com.omarassidi.dailypulse.articles.presentation

import com.omarassidi.dailypulse.articles.domain.Article

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)