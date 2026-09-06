package com.heesoo.my_movie.data.mapper

import com.heesoo.my_movie.data.constants.NetworkingConstants
import com.heesoo.my_movie.data.response.DiscoverResponse
import com.heesoo.my_movie.data.response.MovieData
import com.heesoo.my_movie.data.response.SearchResponse
import com.heesoo.my_movie.domain.model.Movie

object MovieListMapper {
    fun responseToData(response: DiscoverResponse): List<Movie> =
        response.resultList.map { it.toMovie() }

    fun responseToData(response: SearchResponse): List<Movie> =
        response.resultList.map { it.toMovie() }

    private fun MovieData.toMovie(): Movie =
        Movie(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            posterUrl = "${NetworkingConstants.IMAGE_BASE_URL}${posterPath}",
            backdropUrl = "${NetworkingConstants.IMAGE_BASE_URL}${backdropPath}",
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            voteCount = voteCount,
            popularity = popularity,
            genreIdList = genreIdList
        )
}
