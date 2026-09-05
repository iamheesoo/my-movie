package com.heesoo.my_movie.data.remote

import com.heesoo.core.network.handleApi
import com.heesoo.my_movie.data.api.MovieApi
import com.heesoo.my_movie.data.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override suspend fun getMovie(movieId: Int, language: String): Flow<MovieResponse> = flow {
        emit(handleApi { movieApi.getMovie(movieId = movieId, language = language) })
    }
}