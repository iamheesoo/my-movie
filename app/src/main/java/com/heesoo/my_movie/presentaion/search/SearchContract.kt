package com.heesoo.my_movie.presentaion.search

import androidx.compose.ui.text.input.TextFieldValue
import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState
import com.heesoo.my_movie.domain.model.Movie

class SearchContract {
    data class State(
        val textFieldValue: TextFieldValue
    ) : UiState

    sealed interface Event : UiEvent {
        data class UpdateQuery(val textFieldValue: TextFieldValue) : Event
        data class Search(val query: String) : Event
        data class ClickMovie(val movie: Movie) : Event
        data object ClickDelete : Event
        data object ClickBackButton : Event
    }

    sealed interface Effect : UiEffect {
        data class GoToDetail(val movie: Movie) : Effect
        data object PopBackStack : Effect
    }
}
