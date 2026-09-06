package com.heesoo.my_movie.presentaion.search.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.presentaion.ui.composable.image.LoadingAsyncImage

@Composable
fun SearchMovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: () -> Unit,
) {
    LoadingAsyncImage(
        data = movie.posterUrl,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    )
}
