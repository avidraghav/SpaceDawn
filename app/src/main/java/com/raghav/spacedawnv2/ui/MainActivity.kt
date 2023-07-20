package com.raghav.spacedawnv2.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.launchesscreen.LaunchesScreen
import com.raghav.spacedawnv2.navigation.Destination
import com.raghav.spacedawnv2.navigation.LaunchesScreen
import com.raghav.spacedawnv2.navigation.RemindersScreen
import com.raghav.spacedawnv2.remindersscreen.RemindersScreen
import com.raghav.spacedawnv2.ui.theme.SpaceDawnTheme
import com.raghav.spacedawnv2.ui.theme.spacing
import com.raghav.spacedawnv2.util.Helpers.Companion.navigateSingleTopTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceDawnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    tonalElevation = MaterialTheme.spacing.small
                ) {
                    SpaceDawnApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceDawnApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(navController)
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LaunchesScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(LaunchesScreen.route) {
                val activity = (LocalContext.current as? Activity)
                val actionLabel by rememberUpdatedState(newValue = stringResource(id = R.string.reminders))
                LaunchesScreen(
                    systemBackButtonClicked = { activity?.finish() },
                    reminderSetSuccessfully = {
                        scope.launch {
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
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                it,
                                withDismissAction = true
                            )
                        }
                    }
                )
            }
            composable(RemindersScreen.route) {
                val snackBarMessage by rememberUpdatedState(newValue = stringResource(R.string.reminder_cancelled_successfully))
                RemindersScreen(
                    onBackPressed = { navController.navigateSingleTopTo(LaunchesScreen.route) },
                    reminderNotCancelled = { message ->
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message.toString(),
                                withDismissAction = true
                            )
                        }
                    },
                    reminderCancelled = {
                        scope.launch {
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
}

@Composable
fun BottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val destinations = listOf(LaunchesScreen, RemindersScreen)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = modifier, tonalElevation = MaterialTheme.spacing.small) {
        destinations.forEach { destination ->
            BottomNavigationItem(
                destination = destination,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.BottomNavigationItem(
    destination: Destination,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == destination.route
        } == true,
        onClick = {
            navController.navigateSingleTopTo(destination.route)
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
        },
        label = { Text(text = destination.label) }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        tonalElevation = MaterialTheme.spacing.small
    ) {
        // LaunchesScreen()
    }
}
