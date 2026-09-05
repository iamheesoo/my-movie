package com.heesoo.my_movie.presentaion.search

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState

class SearchContract {
    data object State: UiState

    sealed interface Event: UiEvent

    sealed interface Effect: UiEffect
}