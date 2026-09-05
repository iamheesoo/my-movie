package com.heesoo.my_movie.presentaion.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.heesoo.my_movie.home.presentation.composable.TopAppBar
import com.heesoo.my_movie.presentaion.HomeListener
import com.heesoo.my_movie.presentaion.home.composable.MovieHorizontalPager
import com.heesoo.my_movie.presentaion.home.composable.RetryContent

@Composable
fun HomePage(viewModel: HomeViewModel, listener: HomeListener) {
    Effect(viewModel = viewModel, listener = listener)

    val movieLazyItems = viewModel.moviePagingFlow.collectAsLazyPagingItems()
    val pagerState = rememberPagerState() { movieLazyItems.itemCount }

    Scaffold(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        topBar = {
            TopAppBar(title = "홈", rightContent = {
                IconButton(
                    onClick = {viewModel.sendEvent(HomeContract.Event.ClickSearch)}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MovieHorizontalPager(
                modifier = Modifier.fillMaxSize(),
                pagerState = pagerState,
                movieLazyItems = movieLazyItems,
                onClick = { viewModel.sendEvent(HomeContract.Event.ClickMovie(movie = it)) }
            )

            when (movieLazyItems.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is LoadState.Error -> {
                    RetryContent(
                        message = "영화 목록을 불러오지 못했습니다.",
                        onRetry = { movieLazyItems.retry() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is LoadState.NotLoading -> {}
            }

            when (movieLazyItems.loadState.append) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp)
                            .size(24.dp),
                        strokeWidth = 2.dp
                    )
                }

                is LoadState.Error -> {
                    RetryContent(
                        message = "다음 페이지를 불러오지 못했습니다.",
                        onRetry = { movieLazyItems.retry() },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp)
                    )
                }

                is LoadState.NotLoading -> {}
            }
        }
    }
}

@Composable
private fun Effect(viewModel: HomeViewModel, listener: HomeListener) {
    val context = LocalContext.current
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.Effect.GoToDetail -> {
                    listener.goToDetail(effect.movie)
                }

                is HomeContract.Effect.GoToSearch -> {
                    listener.goToSearch()
                }
            }
        }
    }
}