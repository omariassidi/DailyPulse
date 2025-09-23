package com.omarassidi.dailypulse.articles.domain.usecase

import com.omarassidi.dailypulse.articles.domain.models.Article

interface ArticlesUseCase {
    suspend fun getArticles(): Result<List<Article>>
}