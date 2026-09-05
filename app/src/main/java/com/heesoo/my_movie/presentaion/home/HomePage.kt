package com.heesoo.my_movie.presentaion.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heesoo.my_movie.home.presentation.composable.TopAppBar

@Composable
fun HomePage(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val movieList = state.movieList

    Scaffold(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        topBar = { TopAppBar(title = "홈")  }
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            viewModel.sendEvent(HomeContract.Event.EntranceScreen)
        }
        Text("$movieList", color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun Effect(viewModel: HomeViewModel) {
    val context = LocalContext.current
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
//            when(effect) {
//
//            }
        }
    }
}