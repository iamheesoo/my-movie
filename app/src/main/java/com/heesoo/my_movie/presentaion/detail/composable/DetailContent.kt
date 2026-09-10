package com.heesoo.my_movie.presentaion.detail.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heesoo.my_movie.domain.model.MovieDetail
import com.heesoo.my_movie.presentaion.ui.composable.movie.GenreChipsRow

@Composable
fun DetailContent(movieDetail: MovieDetail, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { HeaderSection(movieDetail = movieDetail) }
        item {
            RatingRow(
                voteAverage = movieDetail.voteAverage,
                voteCount = movieDetail.voteCount,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
        if (movieDetail.genreList.isNotEmpty()) {
            item {
                GenreChipsRow(
                    genreList = movieDetail.genreList,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
        if (movieDetail.tagline.isNotEmpty()) {
            item {
                Text(
                    text = movieDetail.tagline,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
        item {
            Text(
                text = movieDetail.overview,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
        item {
            InfoGrid(
                movieDetail = movieDetail,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
        if (movieDetail.productionCompanyList.isNotEmpty()) {
            item {
                ProductionCompanyRow(
                    productionCompanyList = movieDetail.productionCompanyList,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}
