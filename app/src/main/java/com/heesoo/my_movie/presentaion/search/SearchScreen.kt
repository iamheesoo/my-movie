package com.heesoo.my_movie.presentaion.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heesoo.my_movie.presentaion.SearchListener
import com.heesoo.my_movie.presentaion.ui.composable.textfield.SearchTextField

@Composable
fun SearchScreen(viewModel: SearchViewModel, listener: SearchListener) {
    Effect(viewModel = viewModel, listener = listener)

    val state by viewModel.state.collectAsStateWithLifecycle()
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
    ) {
        IconButton(onClick = { viewModel.sendEvent(SearchContract.Event.ClickBackButton) }) {
            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
        }
        SearchTextField(
            modifier = Modifier.weight(1f),
            textFieldValue = state.textFieldValue,
            updateTextFieldValue = { viewModel.sendEvent(SearchContract.Event.UpdateQuery(it)) },
            onDone = {},
            onClickDelete = { viewModel.sendEvent(SearchContract.Event.ClickDelete) }
        )
    }
}

@Composable
private fun Effect(viewModel: SearchViewModel, listener: SearchListener) {
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchContract.Effect.PopBackStack -> listener.popBackStack()
            }
        }
    }
}
