package com.heesoo.my_movie.presentaion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    background = Background,
    surface = Surface,
    onPrimary = Color.White,
    onBackground = OnSurface,
    onSurface = OnSurface,
    secondary = Subtext,
    onSecondary = Subtext,
    onError = OnSurface,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    background = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onBackground = Color(0xFF1A1A1A),
    onSurface = Color(0xFF1A1A1A),
    secondary = Color(0xFF757575),
    onSecondary = Color(0xFF757575),
    onError = Color(0xFF1A1A1A),
)

@Composable
fun MymovieTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}