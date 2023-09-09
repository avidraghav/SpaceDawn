package com.raghav.designsystem.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

data class Colors(
    val Green: Color = Color(0xFF009900),
    val Yellow: Color = Color(0xFFb3b300)
)

val LocalColors = staticCompositionLocalOf { Colors() }

val MaterialTheme.colors: Colors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current
