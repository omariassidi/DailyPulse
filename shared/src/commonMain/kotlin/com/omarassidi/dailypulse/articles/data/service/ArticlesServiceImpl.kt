package com.omarassidi.dailypulse.articles.data.service

import com.omarassidi.dailypulse.articles.data.responses.ArticlesResponse
import com.omarassidi.dailypulse.core.network.apiGet
import io.ktor.client.HttpClient

class ArticlesServiceImpl(private val httpClient: HttpClient) : ArticlesService {
    private val country = "us"
    private val category = "technology"
    private val apiKey = "b7ce4986bad5434abbd7a68b059ff0da"

    override suspend fun fetchArticles(): Result<ArticlesResponse> {
        return httpClient.apiGet<ArticlesResponse>(
            endPoint = "/v2/top-headlines",
            params = mapOf("country" to country, "category" to category, "apiKey" to apiKey)
        )
    }
}