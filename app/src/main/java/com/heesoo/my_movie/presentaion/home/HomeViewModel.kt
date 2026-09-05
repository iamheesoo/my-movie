package com.heesoo.my_movie.presentaion.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.heesoo.core.base.BaseMviViewModel
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.usecase.GetDiscoverUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getDiscoverUseCase: GetDiscoverUseCase
) : BaseMviViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>() {

    val moviePagingFlow: Flow<PagingData<Movie>> = getDiscoverUseCase()
        .cachedIn(viewModelScope)

    override fun createState(): HomeContract.State = HomeContract.State

    override fun handleEvent(event: HomeContract.Event) = Unit
}
