package com.heesoo.my_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.heesoo.my_movie.data.constants.NetworkingConstants
import com.heesoo.my_movie.data.paging.DiscoverPagingSource
import com.heesoo.my_movie.data.remote.DiscoverRemoteDataSource
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val discoverRemoteDataSource: DiscoverRemoteDataSource
) : DiscoverRepository {
    override fun getDiscoverPagingFlow(
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean
    ): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = NetworkingConstants.PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                DiscoverPagingSource(
                    discoverRemoteDataSource = discoverRemoteDataSource,
                    language = language,
                    sortBy = sortBy,
                    includeAdult = includeAdult,
                    includeVideo = includeVideo
                )
            }
        ).flow

}
