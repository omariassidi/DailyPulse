package com.omarassidi.dailypulse.articles.data.repository

import com.omarassidi.dailypulse.articles.domain.models.Article

interface ArticlesRepository {
    suspend fun fetchArticles(forceRefresh: Boolean): Result<List<Article>>
}