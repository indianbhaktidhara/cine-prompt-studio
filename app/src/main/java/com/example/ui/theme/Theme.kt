package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val CineDarkColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = CinemaBlack,
    primaryContainer = GoldContainer,
    onPrimaryContainer = OnGoldContainer,
    secondary = AnamorphicCyan,
    onSecondary = CinemaBlack,
    secondaryContainer = Color(0xFF0F3147),
    onSecondaryContainer = Color(0xFFBAE6FD),
    tertiary = NebulaTeal,
    onTertiary = CinemaBlack,
    tertiaryContainer = Color(0xFF0A332C),
    onTertiaryContainer = Color(0xFF99F6E4),
    error = HeartRed,
    onError = Color.White,
    errorContainer = HeartRedContainer,
    onErrorContainer = Color(0xFFFFD1D9),
    background = CinemaBlack,
    onBackground = TextWhitePrimary,
    surface = CinemaDarkBg,
    onSurface = TextWhitePrimary,
    surfaceVariant = CinemaSurface,
    onSurfaceVariant = TextMuted,
    outline = CinemaBorder,
    outlineVariant = Color(0xFF1E2738)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to cinematic dark OTT aesthetic
    content: @Composable () -> Unit
) {
    val colorScheme = CineDarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = CinemaBlack.toArgb()
            window.navigationBarColor = CinemaBlack.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
