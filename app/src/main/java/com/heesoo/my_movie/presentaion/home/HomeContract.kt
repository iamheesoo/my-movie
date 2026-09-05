package com.heesoo.my_movie.presentaion.home

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState

class HomeContract {
    data object State : UiState

    sealed interface Event : UiEvent

    sealed interface Effect : UiEffect
}
