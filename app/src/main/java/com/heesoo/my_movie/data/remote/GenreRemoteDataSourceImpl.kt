package com.heesoo.my_movie.data.remote

import com.heesoo.core.network.handleApi
import com.heesoo.my_movie.data.api.GenreApi
import com.heesoo.my_movie.data.response.GenreListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val genreApi: GenreApi
) : GenreRemoteDataSource {
    override suspend fun getGenreList(language: String): Flow<GenreListResponse> = flow {
        emit(handleApi { genreApi.getGenreList(language = language) })
    }
}
