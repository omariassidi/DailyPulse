package com.omarassidi.dailypulse.articles.data.repository

import com.omarassidi.dailypulse.articles.data.responses.ArticlesResponse
import com.omarassidi.dailypulse.articles.domain.models.Article
import com.omarassidi.dailypulse.core.network.AppFailure
import com.omarassidi.dailypulse.core.network.apiGet
import io.ktor.client.HttpClient

class ArticlesRepositoryImpl(private val httpClient: HttpClient) : ArticlesRepository {
    private val country = "us"
    private val category = "technology"
    private val apiKey = "b7ce4986bad5434abbd7a68b059ff0da"

    override suspend fun fetchArticles(): Result<List<Article>> {
        val response = httpClient.apiGet<ArticlesResponse>(
            endPoint = "/v2/top-headlines",
            params = mapOf("country" to country, "category" to category, "apiKey" to apiKey)
        )
        if (response.isSuccess) {
            val result = response.getOrThrow().articles?.map {
                Article(
                    title = it.title ?: "",
                    description = it.description ?: "",
                    date = it.date ?: "",
                    imageUrl = it.imageUrl ?: ""
                )
            } ?: listOf()
            return Result.success(result)
        } else {
            return Result.failure(response.exceptionOrNull() ?: AppFailure.Unknown)
        }
    }
}