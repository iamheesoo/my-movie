package com.heesoo.my_movie.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val tagline: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val budget: Long,
    val revenue: Long,
    val genreList: List<Genre>,
    val productionCompanyList: List<ProductionCompany>
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val name: String,
    val logoUrl: String
)
