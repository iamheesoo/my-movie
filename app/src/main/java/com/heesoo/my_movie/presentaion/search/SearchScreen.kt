package com.heesoo.my_movie.presentaion.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.heesoo.my_movie.presentaion.SearchListener
import com.heesoo.my_movie.presentaion.home.composable.RetryContent
import com.heesoo.my_movie.presentaion.search.composable.SearchMovieItem
import com.heesoo.my_movie.presentaion.ui.composable.textfield.SearchTextField

@Composable
fun SearchScreen(viewModel: SearchViewModel, listener: SearchListener) {
    Effect(viewModel = viewModel, listener = listener)

    val state by viewModel.state.collectAsStateWithLifecycle()
    val movieLazyItems = viewModel.searchPagingFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) {
            IconButton(onClick = { viewModel.sendEvent(SearchContract.Event.ClickBackButton) }) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
            }
            SearchTextField(
                modifier = Modifier.weight(1f),
                textFieldValue = state.textFieldValue,
                updateTextFieldValue = { viewModel.sendEvent(SearchContract.Event.UpdateQuery(it)) },
                onDone = { viewModel.sendEvent(SearchContract.Event.Search(it)) },
                onClickDelete = { viewModel.sendEvent(SearchContract.Event.ClickDelete) }
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.textFieldValue.text.isNotBlank()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(count = movieLazyItems.itemCount) { index ->
                        val movie = movieLazyItems[index]
                        if (movie != null) {
                            SearchMovieItem(
                                movie = movie,
                                onClick = { viewModel.sendEvent(SearchContract.Event.ClickMovie(movie = movie)) },
                                onClickFavorite = { viewModel.sendEvent(SearchContract.Event.ClickFavorite(movie = movie)) }
                            )
                        }
                    }
                }

                when (movieLazyItems.loadState.refresh) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    is LoadState.Error -> {
                        RetryContent(
                            message = "검색 결과를 불러오지 못했습니다.",
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
}

@Composable
private fun Effect(viewModel: SearchViewModel, listener: SearchListener) {
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchContract.Effect.GoToDetail -> listener.goToDetail(effect.movie)
                is SearchContract.Effect.PopBackStack -> listener.popBackStack()
            }
        }
    }
}
