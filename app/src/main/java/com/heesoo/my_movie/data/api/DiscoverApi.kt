package com.heesoo.my_movie.data.api

import com.heesoo.my_movie.data.response.DiscoverResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverApi {
    @GET("discover/movie")
    suspend fun getDiscover(
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String
    ): Response<DiscoverResponse>
}