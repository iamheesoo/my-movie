package com.heesoo.my_movie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse(
    @SerialName("genres")
    val genreList: List<GenreData> = emptyList()
)
