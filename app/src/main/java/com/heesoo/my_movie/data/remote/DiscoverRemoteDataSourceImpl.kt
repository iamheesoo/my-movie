package com.heesoo.my_movie.data.remote

import com.heesoo.core.network.handleApi
import com.heesoo.my_movie.data.api.DiscoverApi
import com.heesoo.my_movie.data.response.DiscoverResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiscoverRemoteDataSourceImpl @Inject constructor(
    private val discoverApi: DiscoverApi
) : DiscoverRemoteDataSource {
    override suspend fun getDiscover(
        page: Int,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean
    ): Flow<DiscoverResponse> = flow {
        emit(
            handleApi {
                discoverApi.getDiscover(
                    includeAdult = includeAdult,
                    includeVideo = includeVideo,
                    language = language,
                    page = page,
                    sortBy = sortBy
                )
            }
        )
    }
}
