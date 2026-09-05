package com.heesoo.my_movie.data.mapper

import com.heesoo.my_movie.data.constants.NetworkingConstants
import com.heesoo.my_movie.data.response.MovieResponse
import com.heesoo.my_movie.domain.model.Genre
import com.heesoo.my_movie.domain.model.MovieDetail
import com.heesoo.my_movie.domain.model.ProductionCompany

object MovieMapper {
    fun responseToData(response: MovieResponse): MovieDetail =
        MovieDetail(
            id = response.id,
            title = response.title,
            originalTitle = response.originalTitle,
            tagline = response.tagline,
            overview = response.overview,
            posterUrl = "${NetworkingConstants.IMAGE_BASE_URL}${response.posterPath}",
            backdropUrl = "${NetworkingConstants.IMAGE_BASE_URL}${response.backdropPath}",
            releaseDate = response.releaseDate,
            runtime = response.runtime,
            status = response.status,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            popularity = response.popularity,
            budget = response.budget,
            revenue = response.revenue,
            genreList = response.genreList.map { Genre(id = it.id, name = it.name) },
            productionCompanyList = response.productionCompanyList.map {
                ProductionCompany(
                    id = it.id,
                    name = it.name,
                    logoUrl = if (it.logoPath.isEmpty()) "" else "${NetworkingConstants.IMAGE_BASE_URL}${it.logoPath}"
                )
            }
        )
}
