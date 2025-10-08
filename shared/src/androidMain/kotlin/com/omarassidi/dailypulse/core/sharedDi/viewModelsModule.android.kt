package com.omarassidi.dailypulse.core.sharedDi

import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelsModule: Module
    get() = module {
        viewModel { ArticlesViewModel(get()) }
    }