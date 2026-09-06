package com.heesoo.my_movie.data.mapper

import com.heesoo.my_movie.data.local.entity.FavoriteMovieEntity
import com.heesoo.my_movie.domain.model.Movie

object FavoriteMapper {
    fun dataToEntity(movie: Movie, savedAt: Long = System.currentTimeMillis()): FavoriteMovieEntity =
        FavoriteMovieEntity(
            movieId = movie.id,
            title = movie.title,
            posterUrl = movie.posterUrl,
            voteAverage = movie.voteAverage,
            savedAt = savedAt
        )

    fun entityToData(entity: FavoriteMovieEntity): Movie =
        Movie(
            id = entity.movieId,
            title = entity.title,
            originalTitle = "",
            overview = "",
            posterUrl = entity.posterUrl,
            backdropUrl = "",
            releaseDate = "",
            voteAverage = entity.voteAverage,
            voteCount = 0,
            popularity = 0.0,
            genreIdList = emptyList(),
            isFavorite = true
        )
}
