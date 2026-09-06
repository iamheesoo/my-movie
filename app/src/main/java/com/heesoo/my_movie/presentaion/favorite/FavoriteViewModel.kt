package com.heesoo.my_movie.presentaion.favorite

import androidx.lifecycle.viewModelScope
import com.heesoo.core.base.BaseMviViewModel
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.usecase.DeleteFavoriteUseCase
import com.heesoo.my_movie.domain.usecase.GetFavoriteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteListUseCase: GetFavoriteListUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseMviViewModel<FavoriteContract.State, FavoriteContract.Event, FavoriteContract.Effect>() {

    val favoriteListFlow: Flow<List<Movie>> = getFavoriteListUseCase()

    override fun createState(): FavoriteContract.State = FavoriteContract.State

    override fun handleEvent(event: FavoriteContract.Event) {
        when (event) {
            is FavoriteContract.Event.ClickDeleteFavorite -> {
                deleteFavorite(movie = event.movie)
            }
        }
    }

    private fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            deleteFavoriteUseCase(movieId = movie.id)
        }
    }
}
