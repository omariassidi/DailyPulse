package com.omarassidi.dailypulse.articles.domain.usecase

import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepository
import com.omarassidi.dailypulse.articles.domain.models.Article

class ArticlesUseCaseImpl(private val repository: ArticlesRepository) : ArticlesUseCase {
    override suspend fun getArticles(): Result<List<Article>> {
        return repository.fetchArticles()
    }
}