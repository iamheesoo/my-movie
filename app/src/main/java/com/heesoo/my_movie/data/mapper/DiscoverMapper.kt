package com.heesoo.my_movie.data.mapper

import com.heesoo.my_movie.data.response.DiscoverResponse
import com.heesoo.my_movie.domain.model.Movie

object DiscoverMapper {
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

    fun responseToData(response: DiscoverResponse): List<Movie> =
        response.resultList.map {
            Movie(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterUrl = "$IMAGE_BASE_URL${it.posterPath}",
                backdropUrl = "$IMAGE_BASE_URL${it.backdropPath}",
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                popularity = it.popularity,
                genreIdList = it.genreIdList
            )
        }
}
