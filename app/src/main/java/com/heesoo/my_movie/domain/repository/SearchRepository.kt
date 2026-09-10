package com.heesoo.my_movie.domain.repository

import androidx.paging.PagingData
import com.heesoo.my_movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchListFlow(query: String, language: String): Flow<PagingData<Movie>>
}