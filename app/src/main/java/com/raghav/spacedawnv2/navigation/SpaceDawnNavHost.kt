package com.raghav.spacedawnv2.navigation

import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.launchesscreen.LaunchesScreen
import com.raghav.spacedawnv2.remindersscreen.RemindersScreen
import com.raghav.spacedawnv2.util.Helpers.Companion.navigateSingleTopTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SpaceDawnNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    NavHost(
        navController = navController,
        startDestination = LaunchesScreen.route,
        modifier = modifier
    ) {
        // Launches Screen
        composable(
            LaunchesScreen.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            val activity = (LocalContext.current as? Activity)
            val actionLabel by rememberUpdatedState(
                newValue = stringResource(id = R.string.reminders)
            )
            LaunchesScreen(
                systemBackButtonClicked = { activity?.finish() },
                reminderSetSuccessfully = {
                    coroutineScope.launch {
                        val actionTaken = snackbarHostState.showSnackbar(
                            it,
                            actionLabel = actionLabel,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                        when (actionTaken) {
                            SnackbarResult.ActionPerformed -> {
                                navController.navigateSingleTopTo(RemindersScreen.route)
                            }

                            else -> {}
                        }
                    }
                },
                reminderNotSet = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            it,
                            withDismissAction = true
                        )
                    }
                }
            )
        }
        // Reminders Screen
        composable(
            RemindersScreen.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            val snackBarMessage by rememberUpdatedState(
                newValue = stringResource(R.string.reminder_cancelled_successfully)
            )
            RemindersScreen(
                onBackPressed = { navController.navigateSingleTopTo(LaunchesScreen.route) },
                reminderNotCancelled = { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message.toString(),
                            withDismissAction = true
                        )
                    }
                },
                reminderCancelled = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            // need to extract to string res
                            snackBarMessage,
                            withDismissAction = true
                        )
                    }
                }
            )
        }
    }
}
