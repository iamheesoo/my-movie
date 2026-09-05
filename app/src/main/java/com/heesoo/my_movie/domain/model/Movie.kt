package com.heesoo.my_movie.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val genreIdList: List<Int>
)
