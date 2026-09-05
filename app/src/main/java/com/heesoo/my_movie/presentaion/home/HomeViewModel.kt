package com.heesoo.my_movie.presentaion.home

import androidx.lifecycle.viewModelScope
import com.heesoo.core.base.BaseMviViewModel
import com.heesoo.core.extensions.actionWithLoading
import com.heesoo.core.helper.StateHelper
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.usecase.GetDiscoverUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiscoverUseCase: GetDiscoverUseCase
) : BaseMviViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(), StateHelper {
    override fun createState(): HomeContract.State {
        return HomeContract.State(
            isLoading = false,
            movieList = emptyList()
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.EntranceScreen -> {
                handleEntrance()
            }
        }
    }

    private fun handleEntrance() {
        viewModelScope.launch { getMovieList()}
    }

    private suspend fun getMovieList() {
        actionWithLoading {
            getDiscoverUseCase()
                .catch { it.printStackTrace() }
                .collectLatest { list ->
                    updateMovieList(list)
                }
        }
    }

    override fun updateIsLoading(isVisible: Boolean) {
        setState { this.copy(isLoading = isVisible) }
    }

    private fun updateMovieList(list: List<Movie>) {
        setState { this.copy(movieList = list) }
    }
}