package com.heesoo.my_movie.presentaion

import com.heesoo.my_movie.domain.model.Movie

interface FavoriteListener {
    fun goToDetail(movie: Movie)
}
