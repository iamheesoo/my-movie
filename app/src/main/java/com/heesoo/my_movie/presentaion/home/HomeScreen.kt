package com.heesoo.my_movie.presentaion.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heesoo.my_movie.home.presentation.composable.TopAppBar
import com.heesoo.my_movie.presentaion.home.composable.MovieHorizontalPager

@Composable
fun HomePage(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val movieList = state.movieList
    val pagerState = rememberPagerState() { movieList.size }

    Scaffold(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        topBar = { TopAppBar(title = "홈") }
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            viewModel.sendEvent(HomeContract.Event.EntranceScreen)
        }
        Box(modifier = Modifier.padding(innerPadding)) {
            MovieHorizontalPager(pagerState = pagerState, movieList = movieList)
        }
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