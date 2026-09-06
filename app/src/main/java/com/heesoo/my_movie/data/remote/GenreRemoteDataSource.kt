package com.heesoo.my_movie.data.remote

import com.heesoo.my_movie.data.response.GenreListResponse

interface GenreRemoteDataSource {
    suspend fun getGenreList(language: String): GenreListResponse
}
