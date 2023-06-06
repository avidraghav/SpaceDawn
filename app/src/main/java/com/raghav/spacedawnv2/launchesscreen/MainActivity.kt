package com.raghav.spacedawnv2.launchesscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raghav.spacedawnv2.navigation.LaunchesScreen
import com.raghav.spacedawnv2.navigation.RemindersScreen
import com.raghav.spacedawnv2.navigation.navigationBarScreens
import com.raghav.spacedawnv2.remindersscreen.RemindersScreen
import com.raghav.spacedawnv2.ui.theme.SpaceDawnTheme
import com.raghav.spacedawnv2.ui.theme.spacing
import com.raghav.spacedawnv2.util.Helpers.Companion.navigateSingleTopTo
import dagger.hilt.android.AndroidEntryPoint

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

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                navigationBarScreens.forEach { destination ->
                    NavigationBarItem(
                        selected = currentScreen == destination,
                        onClick = {
                            navController.navigateSingleTopTo(destination.route)
                        },
                        icon = {
                            when (destination) {
                                is LaunchesScreen -> Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null
                                )

                                is RemindersScreen -> Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text(text = destination.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LaunchesScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(LaunchesScreen.route) {
                LaunchesScreen {
                    navController.navigateSingleTopTo(RemindersScreen.route)
                }
            }
            composable(RemindersScreen.route) {
                RemindersScreen()
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
