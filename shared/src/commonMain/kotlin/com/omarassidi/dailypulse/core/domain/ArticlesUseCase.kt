package com.omarassidi.dailypulse.core.domain

import com.omarassidi.dailypulse.articles.domain.Article

interface ArticlesUseCase {
    suspend fun getArticles(): Result<List<Article>>
}