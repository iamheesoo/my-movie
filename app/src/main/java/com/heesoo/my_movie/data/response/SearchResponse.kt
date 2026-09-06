package com.heesoo.my_movie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val resultList: List<MovieData> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)
