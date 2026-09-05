package com.heesoo.my_movie.presentaion.ui.composable.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun LoadingAsyncImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    data: Any,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = {
            Box {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSecondary,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(16.dp)
                )
            }
        },
        error = {
            Box {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.Center),
                )
            }
        },
        modifier = modifier
    )
}