package com.heesoo.my_movie.presentaion.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heesoo.my_movie.presentaion.DetailListener
import com.heesoo.my_movie.presentaion.detail.composable.DetailContent
import com.heesoo.my_movie.presentaion.home.composable.RetryContent

@Composable
fun DetailScreen(viewModel: DetailViewModel, listener: DetailListener, modifier: Modifier = Modifier) {
    Effect(viewModel = viewModel, listener = listener)

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        val movieDetail = state.movieDetail
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error != null -> {
                RetryContent(
                    message = state.error.orEmpty(),
                    onRetry = { viewModel.sendEvent(DetailContract.Event.EntranceScreen) },
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            movieDetail != null -> {
                DetailContent(movieDetail = movieDetail)
            }
        }

        IconButton(
            onClick = { viewModel.sendEvent(DetailContract.Event.ClickBackButton) },
            modifier = Modifier
                .safeDrawingPadding()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
private fun Effect(viewModel: DetailViewModel, listener: DetailListener) {
    LaunchedEffect(Unit) {
        viewModel.sendEvent(DetailContract.Event.EntranceScreen)
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DetailContract.Effect.PopBackStack -> listener.popBackStack()
            }
        }
    }
}
