package com.heesoo.my_movie.domain.repository

import com.heesoo.my_movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {
    suspend fun getDiscoverList(
        page: Int,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean
    ): Flow<List<Movie>>
}
