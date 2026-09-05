package com.heesoo.my_movie.domain.usecase

import androidx.paging.PagingData
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiscoverUseCase @Inject constructor(private val discoverRepository: DiscoverRepository) {
    operator fun invoke(
        isIncludeAdult: Boolean = false,
        isIncludeVideo: Boolean = false
    ): Flow<PagingData<Movie>> {
        return discoverRepository.getDiscoverPagingFlow(
            language = LANGUAGE_KOREAN,
            sortBy = POPULARITY_DESC,
            includeAdult = isIncludeAdult,
            includeVideo = isIncludeVideo
        )
    }

    companion object {
        private const val LANGUAGE_KOREAN = "ko-kr"
        private const val POPULARITY_DESC = "popularity.desc"
    }
}
