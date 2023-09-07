package com.raghav.spacedawnv2.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Extension functions which can be used throughout the app module
 * to prevent the developer from adding repetitive and boiler plate code.
 */
class Helpers {
    companion object {

        fun NavHostController.navigateSingleTopTo(route: String) =
            this.navigate(route) {
                popUpTo(
                    this@navigateSingleTopTo.graph.findStartDestination().id
                ) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
                launchSingleTop = true
            }
    }
}
