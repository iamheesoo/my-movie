package com.heesoo.my_movie.domain.usecase

import androidx.paging.PagingData
import com.heesoo.my_movie.domain.constants.AppConstants
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchListUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(query: String): Flow<PagingData<Movie>> =
        searchRepository.getSearchListFlow(query = query, language = AppConstants.LANGUAGE_KOREAN)
}