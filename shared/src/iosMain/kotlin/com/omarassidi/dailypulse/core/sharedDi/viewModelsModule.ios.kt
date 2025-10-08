package com.omarassidi.dailypulse.core.sharedDi

import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

actual val viewModelsModule = module {
    factory<ArticlesViewModel> { ArticlesViewModel(get()) }
}