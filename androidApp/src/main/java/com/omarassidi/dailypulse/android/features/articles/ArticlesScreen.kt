package com.omarassidi.dailypulse.android.features.articles

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.omarassidi.dailypulse.android.main.MyApplicationTheme
import com.omarassidi.dailypulse.articles.domain.models.Article
import com.omarassidi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    onAboutClicked: () -> Unit
) {
    val articlesViewModel: ArticlesViewModel = getViewModel()
    val state by articlesViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            AppBar(onAboutClicked = onAboutClicked)
        }
    ) {
        Column(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(
                8.dp, alignment = Alignment.CenterVertically
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.errorMessage != null) {
                ErrorMessage(errorMessage = state.errorMessage!!)
            }
            if (state.articles.isNotEmpty()) {
                ArticlesListView(articles = state.articles, isLoading = state.isLoading){ articlesViewModel.getArticles(true) }
            }
        }
    }

}
@Composable
fun ArticlesListView(articles: List<Article>, isLoading: Boolean, onRefresh: () -> Unit) {

    SwipeRefresh(
        state = SwipeRefreshState(isLoading),
        onRefresh = onRefresh) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles) { article ->
                ArticleRowView(article = article)
            }
        }
    }
}


@Composable
fun ArticleRowView(modifier: Modifier = Modifier, article: Article) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            onState = {
                if (it is AsyncImagePainter.State.Loading) {
                    Log.i("AsyncImagePainter", "Loading")
                } else if (it is AsyncImagePainter.State.Error) {
                    Log.i("AsyncImagePainter", "Error ${it.result.throwable.localizedMessage}")
                } else if (it is AsyncImagePainter.State.Success) {
                    Log.i("AsyncImagePainter", "Success")
                } else if (it is AsyncImagePainter.State.Empty) {
                    Log.i("AsyncImagePainter", "Empty")
                }
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.description)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date, style = TextStyle(color = Color.Gray), modifier = Modifier.align(
                Alignment.End
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

    }
}

@Composable
fun ErrorMessage(modifier: Modifier = Modifier, errorMessage: String) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = errorMessage,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center, color = Color.White)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier, onAboutClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Articles", style = MaterialTheme.typography.headlineLarge) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {
            IconButton(onClick = onAboutClicked) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "About")
            }
        }
    )
}

@Preview
@Composable
fun ArticlesScreenPreview() {
    MyApplicationTheme {
        ArticlesScreen(modifier = Modifier.fillMaxSize()) {}
    }
}