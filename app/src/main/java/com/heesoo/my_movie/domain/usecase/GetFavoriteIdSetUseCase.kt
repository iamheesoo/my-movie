package com.heesoo.my_movie.domain.usecase

import com.heesoo.my_movie.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteIdSetUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flow<Set<Int>> = favoriteRepository.getFavoriteIdSet()
}
