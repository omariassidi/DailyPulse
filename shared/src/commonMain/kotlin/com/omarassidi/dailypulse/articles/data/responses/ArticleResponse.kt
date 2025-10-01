package com.omarassidi.dailypulse.articles.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    @SerialName("status")
    val status: String? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null,
    @SerialName("articles")
    val articles: List<ArticleResponse>? = null
)

@Serializable
data class ArticleResponse(
    @SerialName("title")
    val title: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("publishedAt")
    val date: String? = null,
    @SerialName("urlToImage")
    val imageUrl: String? = null
)