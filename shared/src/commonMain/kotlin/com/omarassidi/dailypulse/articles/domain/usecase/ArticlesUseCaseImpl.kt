package com.omarassidi.dailypulse.articles.domain.usecase

import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepository
import com.omarassidi.dailypulse.articles.domain.models.Article
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCaseImpl(private val repository: ArticlesRepository) : ArticlesUseCase {
    override suspend fun getArticles(forceRefresh: Boolean): Result<List<Article>> {
        return repository.fetchArticles(forceRefresh).map {
            it.map { article ->
                article.copy(date = parsePublishedDate(article.date))
            }
        }
    }

    private fun parsePublishedDate(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = abs(
            today.daysUntil(
                Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
            )
        )

        return when {
            days > 1 -> "${days} days ago."
            days == 1 -> "Yesterday"
            else -> "Today"
        }
    }
}