package com.heesoo.my_movie.data.repository

import com.heesoo.core.IoDispatcher
import com.heesoo.my_movie.data.mapper.GenreMapper
import com.heesoo.my_movie.data.remote.GenreRemoteDataSource
import com.heesoo.my_movie.domain.model.Genre
import com.heesoo.my_movie.domain.repository.GenreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GenreRepository {
    private var cachedGenreList: List<Genre>? = null
    private val mutex = Mutex()

    override suspend fun getGenreList(language: String): List<Genre> =
        withContext(ioDispatcher) {
            mutex.withLock {
                cachedGenreList ?: GenreMapper.responseToData(
                    genreRemoteDataSource.getGenreList(language = language)
                ).also { cachedGenreList = it }
            }
        }
}
