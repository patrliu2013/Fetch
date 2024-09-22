package com.example.fetch.ui.welcome

import com.example.base.ComposeViewModel

class FetchWelcomeViewModel: ComposeViewModel<FetchWelcomeContract.Event, FetchWelcomeContract.State>() {
    override fun createInitialState(): FetchWelcomeContract.State {
        return FetchWelcomeContract.State(
            navigationState = FetchWelcomeContract.NavigationState.Idle
        )
    }

    override fun handleEvent(event: FetchWelcomeContract.Event) {
        when(event) {
            FetchWelcomeContract.Event.OnGetListClicked -> {
                setState {
                    copy(
                        navigationState = FetchWelcomeContract.NavigationState.NavigateToList
                    )
                }
            }

            FetchWelcomeContract.Event.OnResetNavigation -> {
                setState {
                    copy(
                        navigationState = FetchWelcomeContract.NavigationState.Idle
                    )
                }
            }
        }
    }
}