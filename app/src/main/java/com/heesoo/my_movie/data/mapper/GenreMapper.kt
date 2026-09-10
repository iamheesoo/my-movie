package com.heesoo.my_movie.data.mapper

import com.heesoo.my_movie.data.response.GenreListResponse
import com.heesoo.my_movie.domain.model.Genre

object GenreMapper {
    fun responseToData(response: GenreListResponse): List<Genre> =
        response.genreList.map { Genre(id = it.id, name = it.name) }
}
