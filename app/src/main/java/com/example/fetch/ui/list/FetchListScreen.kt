package com.example.fetch.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base.Default_Spacing
import com.example.base.Half_Default_Spacing
import com.example.domain.models.FetchItem
import com.example.domain.models.FetchLists
import com.example.fetch.R
import org.koin.androidx.compose.getViewModel

@Composable
fun FetchListScreen() {
    val viewModel = getViewModel<FetchListViewModel>()
    val state = viewModel.uiState.value
    val setEvent: (FetchListContract.Event) -> Unit = { event ->
        viewModel.setEvent(event)
    }

    when(state.listState) {
        FetchListContract.ListState.Error -> {
            // Show Error State
        }
        FetchListContract.ListState.Loading -> {
            // Show Loading State
        }
        is FetchListContract.ListState.Success -> {
            FetchListView(
                lists = state.listState.lists,
                currentExpandedLists = state.currentExpandedLists,
                setEvent = setEvent
            )
        }
        FetchListContract.ListState.SuccessNoData -> {
            // Show No Data State
        }
    }

}

@Composable
private fun FetchListView(
    lists: FetchLists,
    currentExpandedLists: List<Int>,
    setEvent: (FetchListContract.Event) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState,
    ) {
        lists.mapOfLists.forEach { (listId, list) ->
            val isExpanded = currentExpandedLists.contains(listId)
            item {
                FetchListHeader(
                    id = listId,
                    isExpanded = isExpanded,
                    onHeaderClick = {
                        setEvent(
                            if (isExpanded)
                                FetchListContract.Event.OnCollapseListClicked(listId)
                            else
                                FetchListContract.Event.OnExpandListClicked(listId)
                        )
                    }
                )

                Divider(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (isExpanded) {
                itemsIndexed(list) { index, item ->
                    FetchListItem(
                        item = item
                    )

                    if (index == list.lastIndex) {
                        Divider(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FetchListHeader(
    id: Int,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit
) {
    val iconSize = 16.dp
    Row(
        modifier = Modifier
            .padding(horizontal = Default_Spacing, vertical = Half_Default_Spacing)
            .fillMaxWidth()
            .clickable {
                onHeaderClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(iconSize)
                .rotate(if (isExpanded) -90f else 180f),
            colorFilter = ColorFilter.tint(Color.Black)
        )

        Spacer(modifier = Modifier.width(Half_Default_Spacing))

        Text(
            text = stringResource(R.string.header_item, id),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }
}

@Composable
private fun FetchListItem(
    item: FetchItem,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = Default_Spacing, vertical = Half_Default_Spacing)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(Default_Spacing))
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 24.sp,
            ),
        )
    }
}