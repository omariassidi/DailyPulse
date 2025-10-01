package com.omarassidi.dailypulse.articles.di

import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepository
import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepositoryImpl
import com.omarassidi.dailypulse.articles.domain.usecase.ArticlesUseCase
import com.omarassidi.dailypulse.articles.domain.usecase.ArticlesUseCaseImpl
import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    factory<ArticlesUseCase> { ArticlesUseCaseImpl(get()) }
    factory<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
    factory<ArticlesViewModel> { ArticlesViewModel(get()) }
}