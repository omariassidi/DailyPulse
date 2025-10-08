package com.omarassidi.dailypulse.articles.data.localdb

import com.omarassidi.dailypulse.articles.domain.models.Article
import com.omarassidi.dailypulse.db.ArticleTable

interface ArticlesDataSource {

    fun getAllArticles(): List<ArticleTable>

    fun insertArticles(articles: List<ArticleTable>)

    suspend fun deleteAllArticles()

    fun insertArticle(article: ArticleTable)
}