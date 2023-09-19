package com.raghav.spacedawnv2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Available destinations in the app
 */
interface Destination {
    val route: String
    val label: String
    val icon: ImageVector
}

object LaunchesScreen : Destination {
    override val route = "launches_list"
    override val label = "Launches"
    override val icon = Icons.Default.Menu
}

object RemindersScreen : Destination {
    override val route = "reminders_list"
    override val label = "Reminders"
    override val icon = Icons.Default.Notifications
}
