package com.heesoo.my_movie.domain.usecase

import com.heesoo.core.base.Resource
import com.heesoo.core.extensions.asResource
import com.heesoo.my_movie.domain.constants.AppConstants
import com.heesoo.my_movie.domain.model.MovieDetail
import com.heesoo.my_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> {
        return movieRepository.getMovie(movieId = movieId, language = AppConstants.LANGUAGE_KOREAN)
            .asResource()
    }
}
