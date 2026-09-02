package com.heesoo.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<State : UiState, Event : UiEvent, Effect : UiEffect> : ViewModel() {

    private val _state: MutableStateFlow<State> by lazy {
        MutableStateFlow(createState())
    }
    val state by lazy {
        _state.asStateFlow()
    }

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    abstract fun createState(): State

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: Event)

    protected fun sendEffect(effect: () -> Effect) {
        val effectValue = effect()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun sendEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reduce: State.() -> State) {
        val newState = _state.value.reduce()
        _state.value = newState
    }

}

interface UiState
interface UiEvent
interface UiEffect