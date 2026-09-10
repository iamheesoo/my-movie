package com.heesoo.my_movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heesoo.my_movie.data.local.dao.FavoriteMovieDao
import com.heesoo.my_movie.data.local.entity.FavoriteMovieEntity

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        const val DATABASE_NAME = "my_movie_database"
    }
}
