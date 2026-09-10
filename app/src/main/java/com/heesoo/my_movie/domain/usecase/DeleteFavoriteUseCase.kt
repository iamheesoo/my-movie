package com.heesoo.my_movie.domain.usecase

import com.heesoo.my_movie.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(movieId: Int) = favoriteRepository.deleteFavorite(movieId = movieId)
}
