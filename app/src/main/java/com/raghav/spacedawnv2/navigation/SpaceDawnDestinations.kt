package com.raghav.spacedawnv2.navigation

/**
 * Available destinations in the app
 */
interface Destination {
    val route: String
    val label: String
}

object LaunchesScreen : Destination {
    override val route = "launches_list"
    override val label = "Launches"
}

object RemindersScreen : Destination {
    override val route = "reminders_list"
    override val label = "Reminders"
}

val navigationBarScreens = listOf(LaunchesScreen, RemindersScreen)
