package com.raghav.spacedawnv2.remindersscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.remindersscreen.components.RemindersScreenItem
import com.raghav.spacedawnv2.ui.theme.spacing

/**
 * Screen that displays the upcoming Launch Reminders
 * and allows the end user to delete/cancel them
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    modifier: Modifier = Modifier,
    viewModel: RemindersScreenVM = hiltViewModel(),
    onBackPressed: () -> Unit,
    reminderCancelled: () -> Unit,
    reminderNotCancelled: (String?) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                RemindersScreenEvent.ReminderCancelled -> {
                    reminderCancelled()
                }

                is RemindersScreenEvent.ReminderNotCancelled -> {
                    reminderNotCancelled(event.infoMessage)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    FilledIconButton(
                        onClick = onBackPressed,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Icon(
                            imageVector =  Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                title = { Text(stringResource(R.string.reminders)) }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.fillMaxSize().padding(innerPadding)) {
            if (state.reminders.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = MaterialTheme.spacing.small)
                ) {
                    items(state.reminders) { item ->
                        RemindersScreenItem(
                            reminder = item,
                            cancelReminderClicked = { reminderId ->
                                viewModel.cancelReminder(reminderId)
                            }
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                    }
                }
            } else {
                Text(
                    text = stringResource(id = R.string.no_reminders_yet),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.infoMessage?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                        .padding(MaterialTheme.spacing.medium)
                )
            }
        }
    }
}
