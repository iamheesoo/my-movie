package com.heesoo.my_movie.presentaion.search

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.heesoo.core.base.BaseMviViewModel
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.usecase.AddFavoriteUseCase
import com.heesoo.my_movie.domain.usecase.DeleteFavoriteUseCase
import com.heesoo.my_movie.domain.usecase.GetFavoriteIdSetUseCase
import com.heesoo.my_movie.domain.usecase.GetSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchListUseCase: GetSearchListUseCase,
    private val getFavoriteIdSetUseCase: GetFavoriteIdSetUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseMviViewModel<SearchContract.State, SearchContract.Event, SearchContract.Effect>() {

    private val queryFlow = MutableStateFlow("")

    val searchPagingFlow: Flow<PagingData<Movie>> = queryFlow
        .debounce(DEBOUNCE_MILLIS)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) flowOf(PagingData.empty()) else getSearchListUseCase(query = query)
        }
        .cachedIn(viewModelScope)
        .combine(getFavoriteIdSetUseCase()) { pagingData, favoriteIdSet ->
            pagingData.map { movie -> movie.copy(isFavorite = movie.id in favoriteIdSet) }
        }

    override fun createState(): SearchContract.State =
        SearchContract.State(textFieldValue = TextFieldValue(""))

    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.UpdateQuery -> {
                handleUpdateQuery(value = event.textFieldValue)
            }

            is SearchContract.Event.Search -> {
                handleSearch(query = event.query)
            }

            is SearchContract.Event.ClickMovie -> {
                goToDetail(movie = event.movie)
            }

            is SearchContract.Event.ClickFavorite -> {
                toggleFavorite(movie = event.movie)
            }

            is SearchContract.Event.ClickDelete -> {
                clearTextFieldValue()
            }

            is SearchContract.Event.ClickBackButton -> {
                popBackStack()
            }
        }
    }

    private fun handleUpdateQuery(value: TextFieldValue) {
        updateTextFieldValue(value = value)
        queryFlow.value = value.text
    }

    private fun handleSearch(query: String) {
        queryFlow.value = query
    }

    private fun clearTextFieldValue() {
        updateTextFieldValue(value = TextFieldValue(""))
    }

    private fun updateTextFieldValue(value: TextFieldValue) {
        setState { copy(textFieldValue = value) }
    }

    private fun goToDetail(movie: Movie) {
        sendEffect { SearchContract.Effect.GoToDetail(movie = movie) }
    }

    private fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                deleteFavoriteUseCase(movieId = movie.id)
            } else {
                addFavoriteUseCase(movie = movie)
            }
        }
    }

    private fun popBackStack() {
        sendEffect { SearchContract.Effect.PopBackStack }
    }

    companion object {
        private const val DEBOUNCE_MILLIS = 300L
    }
}
