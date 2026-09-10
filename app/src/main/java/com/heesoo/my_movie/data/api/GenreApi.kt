package com.heesoo.my_movie.data.api

import com.heesoo.my_movie.data.response.GenreListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {
    @GET("genre/movie/list")
    suspend fun getGenreList(
        @Query("language") language: String
    ): Response<GenreListResponse>
}
