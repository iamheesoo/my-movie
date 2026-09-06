package com.heesoo.my_movie.domain.repository

import com.heesoo.my_movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteList(): Flow<List<Movie>>
    fun isFavorite(movieId: Int): Flow<Boolean>
    suspend fun addFavorite(movie: Movie)
    suspend fun deleteFavorite(movieId: Int)
}
