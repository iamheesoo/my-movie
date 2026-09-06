package com.heesoo.my_movie.presentaion.favorite

import com.heesoo.core.base.UiEffect
import com.heesoo.core.base.UiEvent
import com.heesoo.core.base.UiState

class FavoriteContract {
    data object State : UiState

    sealed interface Event : UiEvent

    sealed interface Effect : UiEffect
}
