package com.heesoo.my_movie.presentaion.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun RetryContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = message, color = MaterialTheme.colorScheme.onSecondary)
        Text(text = "재시도", modifier = Modifier.clickable(onClick = onRetry))
    }
}