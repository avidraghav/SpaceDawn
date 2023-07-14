package com.raghav.spacedawnv2.navigation

import androidx.annotation.DrawableRes
import com.raghav.spacedawnv2.R

interface Destination {
    @get:DrawableRes
    val icon: Int
    val route: String
    val label: String
}

object LaunchesScreen : Destination {
    override val icon = R.drawable.ic_launcher_background
    override val route = "launches_list"
    override val label = "Launches"
}

object RemindersScreen : Destination {
    override val icon = R.drawable.ic_launcher_foreground
    override val route = "reminders_list"
    override val label = "Reminders"
}

val navigationBarScreens = listOf(LaunchesScreen, RemindersScreen)
