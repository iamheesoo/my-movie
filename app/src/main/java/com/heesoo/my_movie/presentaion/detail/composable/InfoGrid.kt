package com.heesoo.my_movie.presentaion.detail.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heesoo.my_movie.domain.model.MovieDetail

@Composable
fun InfoGrid(movieDetail: MovieDetail, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        InfoRow(label = "개봉일", value = movieDetail.releaseDate)
        InfoRow(label = "상태", value = movieDetail.status)
        if (movieDetail.budget > 0) {
            InfoRow(label = "제작비", value = "$${"%,d".format(movieDetail.budget)}")
        }
        if (movieDetail.revenue > 0) {
            InfoRow(label = "수익", value = "$${"%,d".format(movieDetail.revenue)}")
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.width(72.dp)
        )
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
