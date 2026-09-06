package com.heesoo.my_movie.presentaion.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heesoo.my_movie.presentaion.FavoriteListener
import com.heesoo.my_movie.presentaion.favorite.composable.FavoriteMovieItem

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel, listener: FavoriteListener) {
    Effect(viewModel = viewModel, listener = listener)

    val movieList by viewModel.favoriteListFlow.collectAsStateWithLifecycle(initialValue = emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        if (movieList.isEmpty()) {
            Text(
                text = "찜한 영화가 없습니다",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = movieList.size) { index ->
                    val movie = movieList[index]
                    FavoriteMovieItem(
                        movie = movie,
                        onClick = { viewModel.sendEvent(FavoriteContract.Event.ClickMovie(movie = movie)) },
                        onClickDelete = { viewModel.sendEvent(FavoriteContract.Event.ClickDeleteFavorite(movie = movie)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun Effect(viewModel: FavoriteViewModel, listener: FavoriteListener) {
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is FavoriteContract.Effect.GoToDetail -> listener.goToDetail(effect.movie)
            }
        }
    }
}
