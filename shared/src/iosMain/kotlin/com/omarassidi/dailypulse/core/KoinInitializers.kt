package com.omarassidi.dailypulse.core

import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import com.omarassidi.dailypulse.core.sharedDi.databaseModule
import com.omarassidi.dailypulse.core.sharedDi.sharedModules
import com.omarassidi.dailypulse.core.sharedDi.viewModelsModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedModules + viewModelsModule + databaseModule)
    }
}

class ArticlesInjector : KoinComponent {
    val viewModel: ArticlesViewModel by inject()
}