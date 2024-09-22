package com.example.fetch.ui.list

import androidx.lifecycle.viewModelScope
import com.example.base.ComposeViewModel
import com.example.base.ResultWrapper
import com.example.domain.usecase.GetFetchListUseCase
import kotlinx.coroutines.launch

class FetchListViewModel(
    private val getFetchListUseCase: GetFetchListUseCase
): ComposeViewModel<FetchListContract.Event, FetchListContract.State>() {
    override fun createInitialState(): FetchListContract.State {
        return FetchListContract.State(
            listState = FetchListContract.ListState.Loading,
            currentExpandedLists = listOf()
        )
    }

    init {
        getList()
    }

    override fun handleEvent(event: FetchListContract.Event) {
        when(event) {
            is FetchListContract.Event.OnCollapseListClicked -> {
                // remove list Id from the expanded list tracker
                val newList = uiState.value.currentExpandedLists.toMutableList()
                newList.remove(event.listId)

                // update state with new list
                setState {
                    copy(
                        currentExpandedLists = newList
                    )
                }
            }
            is FetchListContract.Event.OnExpandListClicked -> {
                // add list Id from the expanded list tracker
                val newList = uiState.value.currentExpandedLists.toMutableList()
                newList.add(event.listId)

                // update state with new list
                setState {
                    copy(
                        currentExpandedLists = newList
                    )
                }
            }
        }
    }

    private fun getList() {
        viewModelScope.launch {
            when(val result = getFetchListUseCase.invoke()) {
                is ResultWrapper.Success -> {
                    setState {
                        copy(
                            listState = FetchListContract.ListState.Success(
                                lists = result.data
                            )
                        )
                    }
                }
                is ResultWrapper.GenericError,
                ResultWrapper.NetworkError -> {
                    setState {
                        copy(
                            listState = FetchListContract.ListState.Error
                        )
                    }
                }
                ResultWrapper.SuccessNoData -> {
                    setState {
                        copy(
                            listState = FetchListContract.ListState.SuccessNoData
                        )
                    }
                }
            }
        }
    }
}