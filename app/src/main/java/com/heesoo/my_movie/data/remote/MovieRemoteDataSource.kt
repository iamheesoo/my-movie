package com.heesoo.my_movie.data.remote

import com.heesoo.my_movie.data.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getMovie(movieId: Int, language: String): Flow<MovieResponse>
}