package com.heesoo.my_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.heesoo.my_movie.data.constants.NetworkingConstants
import com.heesoo.my_movie.data.paging.SearchPagingSource
import com.heesoo.my_movie.data.remote.SearchRemoteDataSource
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
) : SearchRepository {
    override fun getSearchListFlow(
        query: String,
        language: String
    ): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = NetworkingConstants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    searchRemoteDataSource = searchRemoteDataSource,
                    query = query,
                    language = language
                )
            }
        ).flow
}