package com.heesoo.my_movie.domain.repository

import androidx.paging.PagingData
import com.heesoo.my_movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {
    fun getDiscoverPagingFlow(
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean
    ): Flow<PagingData<Movie>>
}
