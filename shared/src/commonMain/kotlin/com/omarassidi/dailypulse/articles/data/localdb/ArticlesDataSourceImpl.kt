package com.omarassidi.dailypulse.articles.data.localdb

import com.omarassidi.dailypulse.db.ArticleTable
import com.omarassidi.dailypulse.db.DailyPulseDatabase
import com.omarassidi.dailypulse.db.DailyPulseDatabaseQueries

class ArticlesDataSourceImpl(private val dataBase: DailyPulseDatabase) : ArticlesDataSource {
    override fun getAllArticles(): List<ArticleTable> {
        return dataBase.dailyPulseDatabaseQueries.selectAllArticles().executeAsList()
    }

    override fun insertArticles(articles: List<ArticleTable>) {
        dataBase.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }

    override suspend fun deleteAllArticles() {
        dataBase.dailyPulseDatabaseQueries.removeAllArticles()
    }

    override fun insertArticle(article: ArticleTable) {
        dataBase.dailyPulseDatabaseQueries.insertArticle(
            article.title,
            article.description,
            article.date,
            article.imageUrl
        )
    }
}