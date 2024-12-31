package com.minhky.takehome.designsystem.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightColorScheme = lightColorScheme(
    primary = Color.White,
    onPrimary = Black900,
    primaryContainer = Black900,
    onPrimaryContainer = Color.White,
    secondary = Color.Gray,
    onSecondary = Color.White,
    secondaryContainer = Color.DarkGray,
    onSecondaryContainer = Color.White,
    tertiary = Color.LightGray,
    onTertiary = Gray800,
    tertiaryContainer = Color.Gray,
    onTertiaryContainer = Black900,
    error = Color.Red,
    onError = Color.White,
    errorContainer = Color.Red,
    onErrorContainer = Color.White,
    background = Color.White,
    onBackground = Color.DarkGray,
    surface = Gray100,
    onSurface = Black900,
    surfaceVariant = Color.LightGray,
    onSurfaceVariant = Black900,
    inverseSurface = Black900,
    inverseOnSurface = Color.White,
    outline = Color.Gray,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkColorScheme = darkColorScheme(
    primary = Black900,
    onPrimary = Color.White,
    primaryContainer = Color.White,
    onPrimaryContainer = Black900,
    secondary = Color.Gray,
    onSecondary = Black900,
    secondaryContainer = Color.LightGray,
    onSecondaryContainer = Black900,
    tertiary = Color.DarkGray,
    onTertiary = Color.White,
    tertiaryContainer = Color.Gray,
    onTertiaryContainer = Color.White,
    error = Color.Red,
    onError = Black900,
    errorContainer = Color.Red,
    onErrorContainer = Black900,
    background = Black900,
    onBackground = Color.White,
    surface = Gray800,
    onSurface = Color.White,
    surfaceVariant = Color.DarkGray,
    onSurfaceVariant = Color.White,
    inverseSurface = Color.White,
    inverseOnSurface = Black900,
    outline = Color.Gray,
)

/**
 * Light  background theme
 */
val LightBackgroundTheme = BackgroundTheme(color = Color.White)

/**
 * Dark  background theme
 */
val DarkBackgroundTheme = BackgroundTheme(color = Color.DarkGray)

/**
 * Composable function to apply the project theme.
 *
 * @param darkTheme A boolean flag indicating whether the dark theme should be used. Defaults to the system's dark theme setting.
 * @param content The composable content to be displayed within the theme.
 */
@Composable
fun ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val backgroundTheme = when {
        darkTheme -> DarkBackgroundTheme
        else -> LightBackgroundTheme
    }

    val tintTheme = TintTheme()

    // Composition locals
    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ProjectTypography,
            content = content
        )
    }
}