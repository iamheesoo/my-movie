package com.heesoo.my_movie.data.remote

import com.heesoo.my_movie.data.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    suspend fun getSearch(query: String, language: String, page: Int): Flow<SearchResponse>
}