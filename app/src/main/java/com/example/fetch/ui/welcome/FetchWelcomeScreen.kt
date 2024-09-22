package com.example.fetch.ui.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetch.R
import org.koin.androidx.compose.getViewModel

@Composable
fun FetchWelcomeScreen(
    onNavigateToList: () -> Unit,
) {
    val viewModel = getViewModel<FetchWelcomeViewModel>()
    val state = viewModel.uiState.value
    val setEvent: (FetchWelcomeContract.Event) -> Unit = { event ->
        viewModel.setEvent(event)
    }

    when(state.navigationState) {
        FetchWelcomeContract.NavigationState.Idle -> {
            // Do Nothing, Default State
        }
        FetchWelcomeContract.NavigationState.NavigateToList -> {
            // Navigate to different screen
            onNavigateToList()
            // Reset Nav state to Idle so that upon return to this screen and viewmodel still exists, navigation wont occur
            setEvent(FetchWelcomeContract.Event.OnResetNavigation)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // Title
        Text(
            text = stringResource(R.string.title_fetch_exercise),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )

        // Button to Navigate to List View
        Button(
            onClick = {
                setEvent(FetchWelcomeContract.Event.OnGetListClicked)
            },
            modifier = Modifier.fillMaxWidth(0.4f),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = stringResource(R.string.button_get_list),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                ),
            )
        }
    }
}