package com.heesoo.my_movie.data.remote

import com.heesoo.core.network.handleApi
import com.heesoo.my_movie.data.api.GenreApi
import com.heesoo.my_movie.data.response.GenreListResponse
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val genreApi: GenreApi
) : GenreRemoteDataSource {
    override suspend fun getGenreList(language: String): GenreListResponse =
        handleApi { genreApi.getGenreList(language = language) }
}
