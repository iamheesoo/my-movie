package com.heesoo.my_movie.domain.repository

import com.heesoo.my_movie.domain.model.Genre

interface GenreRepository {
    suspend fun getGenreList(language: String): List<Genre>
}
