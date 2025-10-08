package com.omarassidi.dailypulse.articles.di

import com.omarassidi.dailypulse.articles.data.localdb.ArticlesDataSource
import com.omarassidi.dailypulse.articles.data.localdb.ArticlesDataSourceImpl
import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepository
import com.omarassidi.dailypulse.articles.data.repository.ArticlesRepositoryImpl
import com.omarassidi.dailypulse.articles.data.service.ArticlesService
import com.omarassidi.dailypulse.articles.data.service.ArticlesServiceImpl
import com.omarassidi.dailypulse.articles.domain.usecase.ArticlesUseCase
import com.omarassidi.dailypulse.articles.domain.usecase.ArticlesUseCaseImpl
import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    factory<ArticlesUseCase> { ArticlesUseCaseImpl(get()) }
    factory<ArticlesRepository> { ArticlesRepositoryImpl(get(), get()) }
    factory<ArticlesService> { ArticlesServiceImpl(get()) }
    factory<ArticlesDataSource> { ArticlesDataSourceImpl(get()) }
}