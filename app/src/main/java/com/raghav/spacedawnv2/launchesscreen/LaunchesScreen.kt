package com.raghav.spacedawnv2.launchesscreen

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.commoncomponents.AlertDialog
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.launchesscreen.components.LaunchesScreenItem
import com.raghav.spacedawnv2.ui.ReminderPermissionContract
import com.raghav.spacedawnv2.ui.theme.spacing

@SuppressLint("InlinedApi")
@Composable
fun LaunchesScreen(
    modifier: Modifier = Modifier,
    viewModel: LaunchesScreenVM = hiltViewModel(),
    systemBackButtonClicked: () -> Unit,
    reminderSetSuccessfully: (String) -> Unit,
    reminderNotSet: (String) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    var launch: LaunchDetail? by remember {
        mutableStateOf(null)
    }

    var isPermissionRationaleDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    val scheduleExactAlarmPermissionLauncher = rememberLauncherForActivityResult(
        contract = ReminderPermissionContract(),
        onResult = { isGranted ->
            if (isGranted) {
                launch?.let {
                    viewModel.setReminder(it)
                }
            }
        }
    )

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LaunchesScreenEvent.ReminderSetSuccessfully -> {
                    reminderSetSuccessfully(Constants.REMINDER_SET)
                }

                is LaunchesScreenEvent.ReminderNotSet -> {
                    reminderNotSet(Constants.REMINDER_NOT_SET)
                }

                is LaunchesScreenEvent.PermissionToSetReminderNotGranted -> {
                    isPermissionRationaleDialogVisible = true
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
                items(state.launches) { item ->
                    LaunchesScreenItem(
                        launch = item,
                        addReminderClicked = { launchDetail ->
                            launch = launchDetail
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
    }

    BackHandler {
        systemBackButtonClicked()
    }

    if (isPermissionRationaleDialogVisible) {
        ShowPermissionRationaleDialog(
            onDismissClick = {
                isPermissionRationaleDialogVisible = false
            },
            onConfirmClick = {
                isPermissionRationaleDialogVisible = false
                scheduleExactAlarmPermissionLauncher.launch(Manifest.permission.SCHEDULE_EXACT_ALARM)
            },
            modifier = modifier
        )
    }
}

@Composable
fun ShowPermissionRationaleDialog(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    AlertDialog(
        onDismissClick = onDismissClick,
        onConfirmClick = onConfirmClick,
        title = R.string.permission_required,
        content = R.string.alarm_permission_rationale,
        modifier = modifier
    )
}
