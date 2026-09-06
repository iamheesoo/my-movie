package com.heesoo.my_movie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heesoo.my_movie.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM ${FavoriteMovieEntity.TABLE_NAME} ORDER BY saved_at DESC")
    fun getAll(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM ${FavoriteMovieEntity.TABLE_NAME} WHERE movie_id = :movieId)")
    fun isFavorite(movieId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteMovieEntity)

    @Query("DELETE FROM ${FavoriteMovieEntity.TABLE_NAME} WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)
}
