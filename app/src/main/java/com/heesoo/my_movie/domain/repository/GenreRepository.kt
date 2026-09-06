package com.heesoo.my_movie.domain.repository

import com.heesoo.my_movie.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getGenreList(language: String): Flow<List<Genre>>
}
