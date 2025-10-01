package com.omarassidi.dailypulse.di

import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import com.omarassidi.dailypulse.core.sharedDi.sharedModules
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import platform.Foundation.NSLog

fun initKoin() {
    startKoin {
        modules(sharedModules)
    }
}

fun initHello() {
    NSLog("Hello")
}

class ArticlesInjector: KoinComponent {
    val viewModel: ArticlesViewModel by inject()
}