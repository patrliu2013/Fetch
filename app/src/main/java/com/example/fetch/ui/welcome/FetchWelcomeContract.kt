package com.example.fetch.ui.welcome

import com.example.base.UiEvent
import com.example.base.UiState

class FetchWelcomeContract {
    sealed class Event : UiEvent {
        object OnGetListClicked: Event()

        // Navigation
        object OnResetNavigation: Event()
    }

    data class State(
        val navigationState: NavigationState
    ) : UiState

    sealed class NavigationState {
        object Idle: NavigationState()
        object NavigateToList: NavigationState()
    }
}