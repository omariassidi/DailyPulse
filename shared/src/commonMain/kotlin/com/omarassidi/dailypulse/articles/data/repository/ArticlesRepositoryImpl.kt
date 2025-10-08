package com.omarassidi.dailypulse.articles.data.repository

import com.omarassidi.dailypulse.articles.data.localdb.ArticlesDataSource
import com.omarassidi.dailypulse.articles.data.localdb.ArticlesDataSourceImpl
import com.omarassidi.dailypulse.articles.data.service.ArticlesService
import com.omarassidi.dailypulse.articles.data.service.ArticlesServiceImpl
import com.omarassidi.dailypulse.articles.domain.models.Article
import com.omarassidi.dailypulse.core.network.AppFailure
import com.omarassidi.dailypulse.db.ArticleTable

class ArticlesRepositoryImpl(
    private val articlesService: ArticlesService,
    private val articlesDataSource: ArticlesDataSource
) : ArticlesRepository {

    override suspend fun fetchArticles(forceRefresh: Boolean): Result<List<Article>> {
        if(!forceRefresh) {
            val localResponse = fetchArticlesFromLocalDb()
            if (!localResponse.isEmpty()) {
                println("Articles from db ${localResponse.size}")
                return Result.success(localResponse)
            }
        }
        val response = articlesService.fetchArticles()
        if (response.isSuccess) {
            val result = response.getOrThrow().articles?.map {
                mapToModel(it.title, it.description, it.date, it.imageUrl)
            } ?: listOf()
            saveIntoLocalDb(result)
            return Result.success(result)
        } else {
            return Result.failure(response.exceptionOrNull() ?: AppFailure.Unknown)
        }
    }

    private fun fetchArticlesFromLocalDb(): List<Article> {
        return articlesDataSource.getAllArticles().map {
            mapToModel(it.title, it.description, it.date, it.imageUrl)
        }
    }

    private fun saveIntoLocalDb(articles: List<Article>) {
        articlesDataSource.insertArticles(articles.map { article ->
            ArticleTable(
                title = article.title,
                description = article.description,
                date = article.date,
                imageUrl = article.imageUrl
            )
        })
    }


    private fun mapToModel(
        title: String?,
        description: String?,
        date: String?,
        imageUrl: String?
    ): Article {
        return Article(
            title = title ?: "",
            description = description ?: "",
            date = date ?: "",
            imageUrl = imageUrl ?: ""
        )
    }
}