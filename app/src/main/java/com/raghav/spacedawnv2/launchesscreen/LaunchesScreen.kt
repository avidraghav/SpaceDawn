package com.raghav.spacedawnv2.launchesscreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.commoncomponent.AlertDialog
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.util.Constants
import com.raghav.spacedawnv2.launchesscreen.components.LaunchesScreenItem
import com.raghav.spacedawnv2.ui.theme.spacing
import com.raghav.spacedawnv2.util.Helpers.Companion.isNull
import com.raghav.spacedawnv2.util.Helpers.Companion.openAppSettings
import com.raghav.spacedawnv2.util.ReminderPermissionContract

/**
 * Screen that displays upcoming Rocket Launches
 * and allows the end users set reminder for them.
 */
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

    val context: Context = LocalContext.current

    var showReminderPermissionRationale: Boolean by remember {
        mutableStateOf(false)
    }

    var showNotificationPermissionRationale: Boolean by remember {
        mutableStateOf(false)
    }

    var isNotificationPermissionDeclined: Boolean by remember {
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

    val postNotificationsPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                launch?.let {
                    viewModel.setReminder(it)
                }
            } else {
                isNotificationPermissionDeclined = true
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
                    reminderNotSet(event.infoMessage ?: Constants.REMINDER_NOT_SET)
                }

                is LaunchesScreenEvent.PermissionToSetReminderNotGranted -> {
                    showReminderPermissionRationale = true
                }

                LaunchesScreenEvent.PermissionToSendNotificationsNotGranted -> {
                    showNotificationPermissionRationale = true
                }

                LaunchesScreenEvent.PermissionsToSendNotificationsAndSetReminderNotGranted -> {
                    showReminderPermissionRationale = true
                    showNotificationPermissionRationale = true
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // If the app is opened for the first time with
        // no internet connection then the LaunchesScreenVM will emit
        // Success event but the received launches list will be empty.
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
        } else {
            if (state.errorMessage.isNull().not()) {
                Text(
                    text = state.errorMessage!!,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(MaterialTheme.spacing.medium)
                )
            }
            if (state.isLoading.not()) {
                Text(
                    text = stringResource(id = R.string.no_upcoming_launches),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    BackHandler {
        systemBackButtonClicked()
    }

    if (showReminderPermissionRationale) {
        ShowPermissionRationaleDialog(
            title = R.string.reminder_permission_required,
            content = R.string.alarm_permission_rationale,
            onDismissClick = {
                showReminderPermissionRationale = false
            },
            onConfirmClick = {
                showReminderPermissionRationale = false
                scheduleExactAlarmPermissionLauncher.launch(Manifest.permission.SCHEDULE_EXACT_ALARM)
            },
            modifier = modifier
        )
    }

    if (showNotificationPermissionRationale) {
        ShowPermissionRationaleDialog(
            title = R.string.notification_permission_required,
            content = R.string.notification_permission_rationale,
            onDismissClick = {
                showNotificationPermissionRationale = false
            },
            onConfirmClick = {
                showNotificationPermissionRationale = false
                if (!isNotificationPermissionDeclined) {
                    postNotificationsPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    (context as Activity).openAppSettings()
                }
            }
        )
    }

    if (isNotificationPermissionDeclined) {
        ShowPermissionRationaleDialog(
            title = R.string.notification_permission_required,
            content = R.string.notification_permission_mandatory_rationale,
            onDismissClick = {
            },
            onConfirmClick = {
                (context as Activity).openAppSettings()
            }
        )
    }
}

@Composable
fun ShowPermissionRationaleDialog(
    @StringRes
    title: Int,
    @StringRes
    content: Int,
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    AlertDialog(
        onDismissClick = onDismissClick,
        onConfirmClick = onConfirmClick,
        title = title,
        content = content,
        modifier = modifier
    )
}
