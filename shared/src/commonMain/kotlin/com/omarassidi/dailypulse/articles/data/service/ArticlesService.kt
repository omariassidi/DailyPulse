package com.omarassidi.dailypulse.articles.data.service

import com.omarassidi.dailypulse.articles.data.responses.ArticlesResponse

interface ArticlesService {
    suspend fun fetchArticles(): Result<ArticlesResponse>
}