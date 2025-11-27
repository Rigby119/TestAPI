package com.app.testapi.presentation.theme

import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFFE91E63)

val SecondaryColor = Color(0xFF673AB7)

val TertiaryColor = Color(0xFF00BCD4)

val BackgroundDark = Color(0xFF1C1B1F)
val SurfaceDark = Color(0xFF1C1B1F)

val OnPrimaryDark = Color.White
val OnSecondaryDark = Color.White
val OnTertiaryDark = Color.White

val OnBackgroundDark = Color(0xFFE6E1E5)

val OnSurfaceDark = Color(0xFFE6E1E5)

val DarkColors =
    androidx.compose.material3.darkColorScheme(
        primary = PrimaryColor,
        onPrimary = OnPrimaryDark,
        primaryContainer = PrimaryColor.copy(alpha = 0.24f),
        onPrimaryContainer = PrimaryColor,
        secondary = SecondaryColor,
        onSecondary = OnSecondaryDark,
        secondaryContainer = SecondaryColor.copy(alpha = 0.24f),
        onSecondaryContainer = SecondaryColor,
        tertiary = TertiaryColor,
        onTertiary = OnTertiaryDark,
        background = BackgroundDark,
        onBackground = OnBackgroundDark,
        surface = SurfaceDark,
        onSurface = OnSurfaceDark,
        surfaceVariant = Color(0xFF49454F),
        onSurfaceVariant = Color(0xFFCAC4D0),
        error = Color(0xFFF2B8B5),
        onError = Color.Black,
        errorContainer = Color(0xFF8C1D18),
        onErrorContainer = Color(0xFFF2B8B5),
    )
