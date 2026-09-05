package com.heesoo.my_movie.presentaion.home

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState
import com.heesoo.my_movie.domain.model.Movie

class HomeContract {
    data class State(
        val isLoading: Boolean,
        val movieList: List<Movie>,
    ): UiState

    sealed interface Event: UiEvent {
        data object EntranceScreen: Event
    }

    sealed interface Effect: UiEffect
}