package com.heesoo.my_movie.data.repository

import com.heesoo.core.IoDispatcher
import com.heesoo.my_movie.data.mapper.GenreMapper
import com.heesoo.my_movie.data.remote.GenreRemoteDataSource
import com.heesoo.my_movie.domain.model.Genre
import com.heesoo.my_movie.domain.repository.GenreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GenreRepository {
    override suspend fun getGenreList(language: String): Flow<List<Genre>> =
        withContext(ioDispatcher) {
            genreRemoteDataSource.getGenreList(language = language)
                .map { GenreMapper.responseToData(it) }
        }
}
