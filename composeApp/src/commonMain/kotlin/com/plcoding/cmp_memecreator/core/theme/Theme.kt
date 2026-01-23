package com.plcoding.cmp_memecreator.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    surfaceContainerLowest = Color(color = 0xFF0F0D13),
    surfaceContainerLow = Color(color = 0xFF1D1B20),
    surfaceContainer = Color(color = 0xFF211F26),
    surfaceContainerHigh = Color(color = 0xFF2B2930),
    outline = Color(color = 0xFF79747E),
    primary = Color(color = 0xFF65558F),
    secondary = Color(color = 0xFFCCC2DC),
    onSurface = Color(color = 0xFFE6E0E9),
    primaryContainer = Color(color = 0xFFEADDFF),
    error = Color(color = 0xFFB3261E),
    onPrimary = Color(color = 0xFF21005D),
)

@Composable
fun MemeCreatorTheme(
    content: @Composable () -> Unit
){
    MaterialTheme(
        content = content,
        typography = Typography,
        colorScheme = DarkColorScheme
    )
}