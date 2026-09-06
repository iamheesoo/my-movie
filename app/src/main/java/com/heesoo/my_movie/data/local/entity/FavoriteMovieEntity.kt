package com.heesoo.my_movie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteMovieEntity.TABLE_NAME)
data class FavoriteMovieEntity(
    @PrimaryKey
    @ColumnInfo("movie_id")
    val movieId: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("poster_url")
    val posterUrl: String,
    @ColumnInfo("vote_average")
    val voteAverage: Double,
    @ColumnInfo("saved_at")
    val savedAt: Long,
) {
    companion object {
        const val TABLE_NAME = "favorite_movie"
    }
}
