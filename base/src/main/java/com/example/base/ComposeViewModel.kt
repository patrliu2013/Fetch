package com.example.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


/**
 * Base Compose ViewModel that follows MVI architecture
 *      State: This is the UIState. Compose view obverses this and updates according to its changes
 *      Events: This is User actions that the View sends to ViewModel.
 * */
abstract class ComposeViewModel<Event: UiEvent, State: UiState> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    private val currentState: State
        get() = uiState.value

    private val _uiState: MutableState<State> = mutableStateOf(initialState)
    val uiState: androidx.compose.runtime.State<State> = _uiState

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        subscribeEvents()
    }

    //Observe the Events flow and handles any new events from the View
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect() {
                handleEvent(it)
            }
        }
    }

    // Creates the initial State when ViewModel is first initialized
    abstract fun createInitialState() : State

    // Handles Events from the View and make changes to UIState accordingly
    abstract fun handleEvent(event: Event)

    // View triggers this method to send to ViewModel
    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    // Updates the UIState
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}

interface UiEvent

interface UiState