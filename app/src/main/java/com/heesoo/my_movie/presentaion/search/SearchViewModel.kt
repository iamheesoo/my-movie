package com.heesoo.my_movie.presentaion.search

import androidx.compose.ui.text.input.TextFieldValue
import com.heesoo.core.base.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseMviViewModel<SearchContract.State, SearchContract.Event, SearchContract.Effect>() {

    override fun createState(): SearchContract.State =
        SearchContract.State(textFieldValue = TextFieldValue(""))

    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.UpdateQuery -> {
                handleUpdateQuery(value = event.textFieldValue)
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
    }

    private fun clearTextFieldValue() {
        updateTextFieldValue(value = TextFieldValue(""))
    }

    private fun updateTextFieldValue(value: TextFieldValue) {
        setState { copy(textFieldValue = value) }
    }

    private fun popBackStack() {
        sendEffect { SearchContract.Effect.PopBackStack }
    }
}
