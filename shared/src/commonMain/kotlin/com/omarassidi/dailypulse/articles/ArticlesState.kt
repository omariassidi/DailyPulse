package com.omarassidi.dailypulse.articles

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)