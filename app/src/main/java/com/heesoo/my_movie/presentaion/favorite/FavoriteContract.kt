package com.heesoo.my_movie.presentaion.favorite

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState
import com.heesoo.my_movie.domain.model.Movie

class FavoriteContract {
    data object State : UiState

    sealed interface Event : UiEvent {
        data class ClickMovie(val movie: Movie) : Event
        data class ClickDeleteFavorite(val movie: Movie) : Event
    }

    sealed interface Effect : UiEffect {
        data class GoToDetail(val movie: Movie) : Effect
    }
}
