package com.heesoo.my_movie.presentaion.favorite.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.presentaion.ui.composable.image.LoadingAsyncImage

@Composable
fun FavoriteMovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: () -> Unit,
    onClickDelete: () -> Unit,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        LoadingAsyncImage(
            data = movie.posterUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onClick)
        )
        IconButton(
            onClick = onClickDelete,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.BottomEnd)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
