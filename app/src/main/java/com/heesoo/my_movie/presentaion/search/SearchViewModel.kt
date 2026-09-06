package com.heesoo.my_movie.presentaion.search

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.heesoo.core.base.BaseMviViewModel
import com.heesoo.my_movie.domain.model.Genre
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.domain.usecase.AddFavoriteUseCase
import com.heesoo.my_movie.domain.usecase.DeleteFavoriteUseCase
import com.heesoo.my_movie.domain.usecase.GetFavoriteIdSetUseCase
import com.heesoo.my_movie.domain.usecase.GetGenreListUseCase
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
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getGenreListUseCase: GetGenreListUseCase
) : BaseMviViewModel<SearchContract.State, SearchContract.Event, SearchContract.Effect>() {

    init {
        loadGenreList()
    }

    private val queryFlow = MutableStateFlow("")
    private val selectedGenreIdFlow = MutableStateFlow<Int?>(null)

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
        .combine(selectedGenreIdFlow) { pagingData, selectedGenreId ->
            if (selectedGenreId == null) {
                pagingData
            } else {
                pagingData.filter { movie -> selectedGenreId in movie.genreIdList }
            }
        }

    override fun createState(): SearchContract.State = SearchContract.State(
        textFieldValue = TextFieldValue(""),
        genreList = emptyList(),
        selectedGenreId = null,
    )

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
                handleFavorite(movie = event.movie)
            }

            is SearchContract.Event.ClickGenre -> {
                handleGenreFilter(genre = event.genre)
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

    private fun loadGenreList() {
        viewModelScope.launch {
            val genreList = getGenreListUseCase()
            updateGenreList(genreList)
        }
    }

    private fun handleFavorite(movie: Movie) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                deleteFavoriteUseCase(movieId = movie.id)
            } else {
                addFavoriteUseCase(movie = movie)
            }
        }
    }

    private fun handleGenreFilter(genre: Genre) {
        val newSelectedGenreId = if (selectedGenreIdFlow.value == genre.id) null else genre.id
        selectedGenreIdFlow.value = newSelectedGenreId
        updateSelectedGenreId(newSelectedGenreId)
    }

    private fun goToDetail(movie: Movie) {
        sendEffect { SearchContract.Effect.GoToDetail(movie = movie) }
    }

    private fun popBackStack() {
        sendEffect { SearchContract.Effect.PopBackStack }
    }

    private fun clearTextFieldValue() {
        updateTextFieldValue(value = TextFieldValue(""))
    }

    private fun updateGenreList(list: List<Genre>) {
        setState { copy(genreList = list) }
    }

    private fun updateTextFieldValue(value: TextFieldValue) {
        setState { copy(textFieldValue = value) }
    }

    private fun updateSelectedGenreId(id: Int?) {
        setState { copy(selectedGenreId = id) }
    }

    companion object {
        private const val DEBOUNCE_MILLIS = 300L
    }
}
