package com.heesoo.my_movie.data.mapper

import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.data.response.DiscoverResponse

object DiscoverMapper {
    fun responseToData(response: DiscoverResponse): List<Movie> =
        response.resultList.map {
            Movie(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                popularity = it.popularity,
                genreIdList = it.genreIdList
            )
        }
}
