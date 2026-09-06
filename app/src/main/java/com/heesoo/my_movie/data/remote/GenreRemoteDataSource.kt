package com.heesoo.my_movie.data.remote

import com.heesoo.my_movie.data.response.GenreListResponse
import kotlinx.coroutines.flow.Flow

interface GenreRemoteDataSource {
    suspend fun getGenreList(language: String): Flow<GenreListResponse>
}
