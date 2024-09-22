package com.example.fetch.ui.list

import com.example.base.UiEvent
import com.example.base.UiState
import com.example.domain.models.FetchLists

class FetchListContract {
    sealed class Event : UiEvent {
        data class OnExpandListClicked(val listId: Int): Event()
        data class OnCollapseListClicked(val listId: Int): Event()
    }

    data class State(
        val listState: ListState,
        val currentExpandedLists: List<Int>
    ) : UiState

    sealed class ListState {
        object Loading: ListState()
        object Error: ListState()
        object SuccessNoData: ListState()
        data class Success(val lists: FetchLists): ListState()
    }
}