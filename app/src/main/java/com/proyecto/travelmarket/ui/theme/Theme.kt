package com.proyecto.travelmarket.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Rojo,
    onPrimary = Blanco,
    background = Blanco,
    surface = BlancoDetalle,
    onBackground = Color.Black,
    onSurface = Color.Black,
    secondary = Gris,
)

private val DarkColorScheme = darkColorScheme(
    primary = Rojo,
    onPrimary = Blanco,
    background = Color.Black,
    surface = Gris,
    onBackground = Blanco,
    onSurface = Blanco,
)

@Composable
fun TravelMarketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
