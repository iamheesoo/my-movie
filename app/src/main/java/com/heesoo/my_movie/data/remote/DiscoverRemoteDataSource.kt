package com.heesoo.my_movie.data.remote

import com.heesoo.my_movie.data.response.DiscoverResponse
import kotlinx.coroutines.flow.Flow

interface DiscoverRemoteDataSource {
    suspend fun getDiscover(
        page: Int,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean
    ): Flow<DiscoverResponse>
}
