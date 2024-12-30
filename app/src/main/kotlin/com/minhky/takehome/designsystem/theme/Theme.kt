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
    onPrimary = Color.Black,
    primaryContainer = Color.Black,
    onPrimaryContainer = Color.White,
    secondary = Color.Gray,
    onSecondary = Color.White,
    secondaryContainer = Color.DarkGray,
    onSecondaryContainer = Color.White,
    tertiary = Color.LightGray,
    onTertiary = Color.Black,
    tertiaryContainer = Color.Gray,
    onTertiaryContainer = Color.Black,
    error = Color.Red,
    onError = Color.White,
    errorContainer = Color.Red,
    onErrorContainer = Color.White,
    background = Color.White,
    onBackground = Color.DarkGray,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Color.LightGray,
    onSurfaceVariant = Color.Black,
    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    outline = Color.Gray,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    primaryContainer = Color.White,
    onPrimaryContainer = Color.Black,
    secondary = Color.Gray,
    onSecondary = Color.Black,
    secondaryContainer = Color.LightGray,
    onSecondaryContainer = Color.Black,
    tertiary = Color.DarkGray,
    onTertiary = Color.White,
    tertiaryContainer = Color.Gray,
    onTertiaryContainer = Color.White,
    error = Color.Red,
    onError = Color.Black,
    errorContainer = Color.Red,
    onErrorContainer = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    surfaceVariant = Color.DarkGray,
    onSurfaceVariant = Color.White,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
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