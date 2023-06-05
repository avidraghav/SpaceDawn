package com.raghav.spacedawnv2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    /**
     * spacing = 4.dp
     */
    val extraSmall: Dp = 4.dp,
    /**
     * spacing = 8.dp
     */
    val small: Dp = 8.dp,
    /**
     * spacing = 16.dp
     */
    val medium: Dp = 16.dp,
    /**
     * spacing = 32.dp
     */
    val large: Dp = 32.dp,
    /**
     * spacing = 64.dp
     */
    val extraLarge: Dp = 64.dp,
    /**
     * spacing = 0.dp
     */
    val default: Dp = 0.dp
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
