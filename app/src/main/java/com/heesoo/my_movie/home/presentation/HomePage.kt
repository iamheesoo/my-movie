package com.heesoo.my_movie.home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.heesoo.my_movie.home.presentation.composable.TopAppBar

@Composable
fun HomePage() {
    Scaffold(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        topBar = { TopAppBar(title = "홈")  }
    ) { innerPadding ->
        Text("home", color = MaterialTheme.colorScheme.primary)
    }
}