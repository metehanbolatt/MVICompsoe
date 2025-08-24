package com.metehanbolat.mvicompsoe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIViewModel<State, Event, Effect>(
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState by lazy(
        initializer = {
            MutableStateFlow(
                value = initialState(savedStateHandle = savedStateHandle)
            )
        }
    )
    val uiState get() = _uiState.asStateFlow()

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    abstract fun handleEvent(event: Event)

    abstract fun initialState(savedStateHandle: SavedStateHandle): State

    protected fun updateState(block: State.() -> State) {
        _uiState.update(function = block)
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch(
            block = { _effect.send(element = effect) }
        )
    }
}
















