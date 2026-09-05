package com.heesoo.my_movie.presentaion.search

import androidx.compose.ui.text.input.TextFieldValue
import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState

class SearchContract {
    data class State(
        val textFieldValue: TextFieldValue
    ) : UiState

    sealed interface Event : UiEvent {
        data class UpdateQuery(val textFieldValue: TextFieldValue) : Event
        data object ClickDelete : Event
        data object ClickBackButton : Event
    }

    sealed interface Effect : UiEffect {
        data object PopBackStack : Effect
    }
}
