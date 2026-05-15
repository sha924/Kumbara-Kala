package com.kumbarakala.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Terracotta,
    onPrimary = Cream,
    secondary = LeafGreen,
    onSecondary = Cream,
    background = Cream,
    onBackground = Charcoal,
    surface = Cream,
    onSurface = Charcoal,
    surfaceVariant = Sand,
    onSurfaceVariant = ClayBrown




)

private val DarkColors = darkColorScheme(
    primary = FiredClay,
    onPrimary = DarkClay,
    secondary = Sand,
    onSecondary = DarkClay,
    background = DarkClay,
    onBackground = Cream,
    surface = Charcoal,
    onSurface = Cream,
    surfaceVariant = ClayBrown,
    onSurfaceVariant = Cream
)

@Composable
fun KumbaraKalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
