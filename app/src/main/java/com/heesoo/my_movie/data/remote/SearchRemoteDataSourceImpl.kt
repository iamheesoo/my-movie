package com.heesoo.my_movie.data.remote

import com.heesoo.core.network.handleApi
import com.heesoo.my_movie.data.api.SearchApi
import com.heesoo.my_movie.data.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRemoteDataSource {
    override suspend fun getSearch(
        query: String,
        language: String,
        page: Int
    ): Flow<SearchResponse> = flow {
        emit(handleApi {
            searchApi.getSearch(
                query = query,
                language = language,
                page = page
            )
        })
    }
}