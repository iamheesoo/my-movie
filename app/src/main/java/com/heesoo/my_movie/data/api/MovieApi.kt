package com.heesoo.my_movie.data.api

import com.heesoo.my_movie.data.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path(value = "movie_id") movieId: Int,
        @Query("language") language: String
    ): Response<MovieResponse>
}