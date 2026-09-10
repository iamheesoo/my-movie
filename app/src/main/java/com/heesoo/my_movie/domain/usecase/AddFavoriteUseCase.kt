package com.heesoo.my_movie.domain.usecase

import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(movie: Movie) = favoriteRepository.addFavorite(movie = movie)
}
