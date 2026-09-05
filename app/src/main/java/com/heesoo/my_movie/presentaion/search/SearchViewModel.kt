package com.heesoo.my_movie.presentaion.search

import com.heesoo.core.base.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): BaseMviViewModel<SearchContract.State, SearchContract.Event, SearchContract.Effect>() {
    override fun createState(): SearchContract.State =
        SearchContract.State

    override fun handleEvent(event: SearchContract.Event) {

    }
}