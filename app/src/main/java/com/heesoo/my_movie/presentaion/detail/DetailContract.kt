package com.heesoo.my_movie.presentaion.detail

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState
import com.heesoo.my_movie.domain.model.MovieDetail

class DetailContract {
    data class State(
        val isLoading: Boolean,
        val movieDetail: MovieDetail?,
        val error: String?
    ) : UiState

    sealed interface Event : UiEvent {
        data object EntranceScreen : Event
        data object ClickBackButton : Event
    }

    sealed interface Effect : UiEffect {
        data object PopBackStack : Effect
    }
}
