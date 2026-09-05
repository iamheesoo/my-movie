package com.heesoo.my_movie.domain.repository

import com.heesoo.my_movie.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovie(movieId: Int, language: String): Flow<MovieDetail>
}
