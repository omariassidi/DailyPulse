package com.omarassidi.dailypulse.core.data

import com.omarassidi.dailypulse.articles.domain.Article

interface ArticlesRepository {
    suspend fun fetchArticles(): Result<List<Article>>
}