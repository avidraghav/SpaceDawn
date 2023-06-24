package com.raghav.spacedawnv2.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raghav.spacedawnv2.launchesscreen.LaunchesScreen
import com.raghav.spacedawnv2.navigation.LaunchesScreen
import com.raghav.spacedawnv2.navigation.RemindersScreen
import com.raghav.spacedawnv2.navigation.navigationBarScreens
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
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        navigationBarScreens.find { it.route == currentDestination?.route } ?: LaunchesScreen

    // to get the currently selected navigation tab
    var selectedTab by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(tonalElevation = MaterialTheme.spacing.small) {
                navigationBarScreens.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = currentScreen == destination,
                        onClick = {
                            selectedTab = index
                            navController.navigateSingleTopTo(destination.route)
                        },
                        icon = {
                            when (destination) {
                                is LaunchesScreen -> Icon(
                                    // temporary icon
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null
                                )

                                is RemindersScreen -> Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text(text = destination.label) }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LaunchesScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(LaunchesScreen.route) {
                val activity = (LocalContext.current as? Activity)
                LaunchesScreen(
                    systemBackButtonClicked = { activity?.finish() },
                    reminderSetSuccessfully = {
                        scope.launch {
                            val actionTaken = snackbarHostState.showSnackbar(
                                it,
                                actionLabel = "Reminders",
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
                RemindersScreen {
                    navController.navigateSingleTopTo(LaunchesScreen.route)
                }
            }
        }
    }
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
