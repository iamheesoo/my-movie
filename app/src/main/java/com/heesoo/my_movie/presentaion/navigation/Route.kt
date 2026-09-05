package com.heesoo.my_movie.presentaion.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data class Detail(val movieId: Int) : Route

    @Serializable
    data object Search: Route
}
