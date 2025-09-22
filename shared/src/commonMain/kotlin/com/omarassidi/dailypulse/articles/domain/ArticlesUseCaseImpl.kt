package com.omarassidi.dailypulse.articles.domain

import com.omarassidi.dailypulse.core.data.ArticlesRepository
import com.omarassidi.dailypulse.core.domain.ArticlesUseCase

class ArticlesUseCaseImpl(private val repository: ArticlesRepository) : ArticlesUseCase {
    override suspend fun getArticles(): Result<List<Article>> {
        return repository.fetchArticles()
    }
}