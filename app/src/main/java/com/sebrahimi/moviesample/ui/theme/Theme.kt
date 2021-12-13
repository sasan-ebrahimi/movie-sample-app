package com.sebrahimi.moviesample.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple300,
    secondary = Teal200,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF333333),
    surface = Color.White
)
// TODO
@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Purple200,
    primaryVariant = Purple300,
    secondary = Teal200,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF333333),
    surface = Color.White
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MovieSampleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,

    )
}