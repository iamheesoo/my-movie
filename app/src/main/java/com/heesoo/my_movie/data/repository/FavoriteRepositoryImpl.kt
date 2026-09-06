package com.heesoo.my_movie.data.repository

import com.heesoo.my_movie.data.local.dao.FavoriteMovieDao
import com.heesoo.my_movie.data.mapper.FavoriteMapper
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : FavoriteRepository {
    override fun getFavoriteList(): Flow<List<Movie>> =
        favoriteMovieDao.getAll().map { entityList ->
            entityList.map { FavoriteMapper.entityToData(it) }
        }

    override fun isFavorite(movieId: Int): Flow<Boolean> =
        favoriteMovieDao.isFavorite(movieId = movieId)

    override fun getFavoriteIdSet(): Flow<Set<Int>> =
        favoriteMovieDao.getFavoriteIdList().map { it.toSet() }

    override suspend fun addFavorite(movie: Movie) {
        favoriteMovieDao.insert(entity = FavoriteMapper.dataToEntity(movie = movie))
    }

    override suspend fun deleteFavorite(movieId: Int) {
        favoriteMovieDao.delete(movieId = movieId)
    }
}
