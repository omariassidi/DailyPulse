//package com.omarassidi.dailypulse.android.main
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.omarassidi.dailypulse.android.features.about.AboutScreen
//import com.omarassidi.dailypulse.android.features.articles.ArticlesScreen
//import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
//
//@Composable
//fun AppScaffold(articlesViewModel: ArticlesViewModel) {
//    val navController = rememberNavController()
//    Scaffold {
//        AppNavHost(
//            modifier = Modifier.padding(it),
//            navController = navController,
//            articlesViewModel = articlesViewModel
//        )
//    }
//
//}
//
//@Composable
//fun AppNavHost(
//    modifier: Modifier = Modifier,
//    navController: NavHostController,
//    articlesViewModel: ArticlesViewModel
//) {
//    NavHost(
//        modifier = modifier,
//        startDestination = Screen.ArticlesScreen.route,
//        navController = navController
//    ) {
//        composable(Screen.ArticlesScreen.route) {
//            ArticlesScreen(articlesViewModel = articlesViewModel) {
//                navController.navigate(route = Screen.AboutScreen)
//            }
//        }
//        composable(Screen.AboutScreen.route) {
//            AboutScreen {
//                navController.navigateUp()
//            }
//        }
//    }
//}