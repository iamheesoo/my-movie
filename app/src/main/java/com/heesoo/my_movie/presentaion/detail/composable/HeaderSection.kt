package com.heesoo.my_movie.presentaion.detail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.heesoo.my_movie.domain.model.MovieDetail
import com.heesoo.my_movie.presentaion.ui.composable.image.LoadingAsyncImage

@Composable
fun HeaderSection(movieDetail: MovieDetail, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        LoadingAsyncImage(
            data = movieDetail.backdropUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background)
                    )
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 160.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            LoadingAsyncImage(
                data = movieDetail.posterUrl,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)) {
                Text(
                    text = movieDetail.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${movieDetail.releaseDate} · ${movieDetail.runtime}분",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}
