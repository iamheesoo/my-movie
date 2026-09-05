package com.heesoo.my_movie.presentaion

import com.heesoo.my_movie.domain.model.Movie

interface HomeListener {
    fun goToDetail(movie: Movie)
    fun goToSearch()
}