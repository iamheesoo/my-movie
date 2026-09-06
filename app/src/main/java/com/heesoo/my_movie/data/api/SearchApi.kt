package com.heesoo.my_movie.data.api

import com.heesoo.my_movie.data.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/movie")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<SearchResponse>
}