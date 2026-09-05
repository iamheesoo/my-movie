package com.heesoo.my_movie.domain.usecase

import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiscoverUseCase @Inject constructor(private val discoverRepository: DiscoverRepository) {
    suspend operator fun invoke(): Flow<List<Movie>> {
        return discoverRepository.getDiscoverList(
            page = 1,
            language = "ko-kr",
            sortBy = "popularity.desc",
            includeAdult = false,
            includeVideo = false
        )
    }
}