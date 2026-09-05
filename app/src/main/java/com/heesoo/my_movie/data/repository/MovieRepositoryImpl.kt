package com.heesoo.my_movie.data.repository

import com.heesoo.core.IoDispatcher
import com.heesoo.my_movie.data.mapper.MovieMapper
import com.heesoo.my_movie.data.remote.MovieRemoteDataSource
import com.heesoo.my_movie.domain.model.MovieDetail
import com.heesoo.my_movie.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override suspend fun getMovie(movieId: Int, language: String): Flow<MovieDetail> =
        withContext(ioDispatcher) {
            movieRemoteDataSource.getMovie(movieId = movieId, language = language)
                .map { MovieMapper.responseToData(it) }
        }
}
