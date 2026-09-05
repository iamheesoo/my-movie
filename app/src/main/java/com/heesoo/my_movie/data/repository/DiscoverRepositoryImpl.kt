package com.heesoo.my_movie.data.repository

import com.heesoo.core.IoDispatcher
import com.heesoo.my_movie.data.mapper.DiscoverMapper
import com.heesoo.my_movie.data.remote.DiscoverRemoteDataSource
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.DiscoverRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val discoverRemoteDataSource: DiscoverRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DiscoverRepository {
    override suspend fun getDiscoverList(
        page: Int,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean
    ): Flow<List<Movie>> =
        withContext(ioDispatcher) {
            discoverRemoteDataSource.getDiscover(
                page = page,
                language = language,
                sortBy = sortBy,
                includeAdult = includeAdult,
                includeVideo = includeVideo
            )
                .map { DiscoverMapper.responseToData(it) }
        }
}
