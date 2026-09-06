package com.heesoo.my_movie.domain.usecase

import com.heesoo.my_movie.domain.constants.AppConstants
import com.heesoo.my_movie.domain.model.Genre
import com.heesoo.my_movie.domain.repository.GenreRepository
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    suspend operator fun invoke(): List<Genre> =
        genreRepository.getGenreList(language = AppConstants.LANGUAGE_KOREAN)
}
