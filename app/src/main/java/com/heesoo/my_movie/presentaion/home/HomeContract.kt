package com.heesoo.my_movie.presentaion.home

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState
import com.heesoo.my_movie.domain.model.Movie

class HomeContract {
    data object State : UiState

    sealed interface Event : UiEvent {
        data class ClickMovie(
            val movie: Movie
        ): Event
    }

    sealed interface Effect : UiEffect {
        data class GoToDetail(val movie: Movie): Effect
    }
}
