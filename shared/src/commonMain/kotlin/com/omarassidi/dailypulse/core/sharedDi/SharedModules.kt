package com.omarassidi.dailypulse.core.sharedDi

import com.omarassidi.dailypulse.articles.di.articlesModule
import com.omarassidi.dailypulse.core.network.di.networkModule

val sharedModules = listOf(articlesModule, networkModule)