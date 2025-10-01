package com.omarassidi.dailypulse.android.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omarassidi.dailypulse.android.features.about.AboutScreen
import com.omarassidi.dailypulse.android.features.articles.ArticlesScreen
import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    AppNavHost(
        modifier = Modifier,
        navController = navController
    )
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        startDestination = Screen.ArticlesScreen.route,
        navController = navController
    ) {
        composable(Screen.ArticlesScreen.route) {
            ArticlesScreen(
                modifier = Modifier.fillMaxSize()
            ) {
                navController.navigate(route = Screen.AboutScreen.route)
            }
        }
        composable(Screen.AboutScreen.route) {
            AboutScreen {
                navController.navigateUp()
            }
        }
    }
}