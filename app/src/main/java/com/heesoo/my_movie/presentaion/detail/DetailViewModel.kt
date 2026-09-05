package com.heesoo.my_movie.presentaion.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.heesoo.core.base.BaseMviViewModel
import com.heesoo.core.base.Resource
import com.heesoo.my_movie.domain.usecase.GetMovieDetailUseCase
import com.heesoo.my_movie.presentaion.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<DetailContract.State, DetailContract.Event, DetailContract.Effect>() {

    private val movieId: Int = savedStateHandle.toRoute<Route.Detail>().movieId

    override fun createState(): DetailContract.State = DetailContract.State(
        isLoading = false,
        movieDetail = null,
        error = null
    )

    override fun handleEvent(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.EntranceScreen -> fetchMovieDetail()
            is DetailContract.Event.ClickBackButton -> {
                popBackStack()
            }
        }
    }

    private fun fetchMovieDetail() {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> setState { copy(isLoading = true) }
                    is Resource.Success -> setState {
                        copy(isLoading = false, movieDetail = resource.data)
                    }

                    is Resource.Error -> setState {
                        copy(isLoading = false, error = resource.throwable.message)
                    }
                }
            }
        }
    }

    private fun popBackStack() {
        sendEffect { DetailContract.Effect.PopBackStack }
    }
}
