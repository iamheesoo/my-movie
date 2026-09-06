package com.heesoo.my_movie.presentaion.favorite

import com.heesoo.core.base.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() :
    BaseMviViewModel<FavoriteContract.State, FavoriteContract.Event, FavoriteContract.Effect>() {

    override fun createState(): FavoriteContract.State = FavoriteContract.State

    override fun handleEvent(event: FavoriteContract.Event) {
    }
}
