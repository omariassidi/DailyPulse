package com.omarassidi.dailypulse.android.features.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.omarassidi.dailypulse.about.Platform


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(onUpButtonClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = "About Device", style = MaterialTheme.typography.headlineLarge) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = onUpButtonClicked) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun AboutScreen(modifier: Modifier = Modifier, onUpButtonClicked: () -> Unit) {
    Scaffold(
        containerColor = Color.Transparent,
        modifier = modifier,
        topBar = {
            Toolbar(onUpButtonClicked)
        }
    ) { innerPadding ->
        AboutContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AboutContent(modifier: Modifier = Modifier) {
    val items = makeItems()
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        items(items) {
            RowView(title = it.first, subTitle = it.second)
            if (items.indexOf(it) != items.size - 1) {
                Divider()
            }
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    return listOf(
        "Operating System" to "${platform.osName} ${platform.osVersion}",
        "Device" to platform.deviceModel,
        "Density" to platform.density.toString()
    )
}

@Composable
fun RowView(modifier: Modifier = Modifier, title: String, subTitle: String) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = title, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Text(text = subTitle, style = MaterialTheme.typography.bodyLarge)
    }
}