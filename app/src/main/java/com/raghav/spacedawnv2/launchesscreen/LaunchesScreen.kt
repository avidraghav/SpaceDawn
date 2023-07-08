package com.raghav.spacedawnv2.launchesscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raghav.spacedawnv2.launchesscreen.components.LaunchesScreenItem
import com.raghav.spacedawnv2.ui.theme.spacing
import com.raghav.spacedawnv2.util.Constants

@Composable
fun LaunchesScreen(
    modifier: Modifier = Modifier,
    viewModel: LaunchesScreenVM = hiltViewModel(),
    systemBackButtonClicked: () -> Unit,
    reminderSetSuccessfully: (String) -> Unit,
    reminderNotSet: (String) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LaunchesScreenVM.LaunchesScreenEvent.ReminderSetSuccessfully -> {
                    reminderSetSuccessfully(Constants.REMINDER_SET)
                }

                LaunchesScreenVM.LaunchesScreenEvent.ReminderNotSet -> {
                    reminderNotSet(Constants.REMINDER_NOT_SET)
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.launches.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MaterialTheme.spacing.small)
            ) {
                items(state.launches) { launch ->
                    LaunchesScreenItem(
                        launch = launch,
                        addReminderClicked = { launchDetail ->
                            viewModel.setReminder(launchDetail)
                        }
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        BackHandler {
            systemBackButtonClicked()
        }
    }
}
