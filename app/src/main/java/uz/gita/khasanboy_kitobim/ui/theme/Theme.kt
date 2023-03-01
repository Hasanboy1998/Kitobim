package uz.gita.khasanboy_kitobim.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import uz.gita.khasanboy_kitobim.data.stateResponseData.TextStyleTypes
import uz.gita.khasanboy_kitobim.data.stateResponseData.ThemesState

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


///////////////////////////////////////////////////

private val SecondLightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
//    outlineVariant = md_theme_light_outlineVariant,
//    scrim = md_theme_light_scrim,
)


private val SecondDarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
//    outlineVariant = md_theme_dark_outlineVariant,
//    scrim = md_theme_dark_scrim,
)
///////////////////////////////////////////


private val ThreeLightColors = lightColorScheme(
    primary = three_light_primary,
    onPrimary = three_light_onPrimary,
    primaryContainer = three_light_primaryContainer,
    onPrimaryContainer = three_light_onPrimaryContainer,
    secondary = three_light_secondary,
    onSecondary = three_light_onSecondary,
    secondaryContainer = three_light_secondaryContainer,
    onSecondaryContainer = three_light_onSecondaryContainer,
    tertiary = three_light_tertiary,
    onTertiary = three_light_onTertiary,
    tertiaryContainer = three_light_tertiaryContainer,
    onTertiaryContainer = three_light_onTertiaryContainer,
    error = three_light_error,
    errorContainer = three_light_errorContainer,
    onError = three_light_onError,
    onErrorContainer = three_light_onErrorContainer,
    background = three_light_background,
    onBackground = three_light_onBackground,
    surface = three_light_surface,
    onSurface = three_light_onSurface,
    surfaceVariant = three_light_surfaceVariant,
    onSurfaceVariant = three_light_onSurfaceVariant,
    outline = three_light_outline,
    inverseOnSurface = three_light_inverseOnSurface,
    inverseSurface = three_light_inverseSurface,
    inversePrimary = three_light_inversePrimary,
    surfaceTint = three_light_surfaceTint,
//    outlineVariant = three_light_outlineVariant,
//    scrim = three_light_scrim,
)


private val ThreeDarkColors = darkColorScheme(
    primary = three_dark_primary,
    onPrimary = three_dark_onPrimary,
    primaryContainer = three_dark_primaryContainer,
    onPrimaryContainer = three_dark_onPrimaryContainer,
    secondary = three_dark_secondary,
    onSecondary = three_dark_onSecondary,
    secondaryContainer = three_dark_secondaryContainer,
    onSecondaryContainer = three_dark_onSecondaryContainer,
    tertiary = three_dark_tertiary,
    onTertiary = three_dark_onTertiary,
    tertiaryContainer = three_dark_tertiaryContainer,
    onTertiaryContainer = three_dark_onTertiaryContainer,
    error = three_dark_error,
    errorContainer = three_dark_errorContainer,
    onError = three_dark_onError,
    onErrorContainer = three_dark_onErrorContainer,
    background = three_dark_background,
    onBackground = three_dark_onBackground,
    surface = three_dark_surface,
    onSurface = three_dark_onSurface,
    surfaceVariant = three_dark_surfaceVariant,
    onSurfaceVariant = three_dark_onSurfaceVariant,
    outline = three_dark_outline,
    inverseOnSurface = three_dark_inverseOnSurface,
    inverseSurface = three_dark_inverseSurface,
    inversePrimary = three_dark_inversePrimary,
    surfaceTint = three_dark_surfaceTint,
//    outlineVariant = three_dark_outlineVariant,
//    scrim = three_dark_scrim,
)

object Theme {
    val isDarkMode = mutableStateOf(false)
    val themeState = mutableStateOf(ThemesState.FIRST_THEME)
    val textStyle = mutableStateOf(TextStyleTypes.MEDIUM)
}

@Composable
fun KitobimTheme(
    themesType: ThemesState = Theme.themeState.value,
    textStyleTypes: TextStyleTypes = Theme.textStyle.value,
    darkTheme: Boolean = Theme.isDarkMode.value/*isSystemInDarkTheme()*/,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val secondColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> SecondDarkColors
        else -> SecondLightColors
    }

    val threeColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> ThreeDarkColors
        else -> ThreeLightColors
    }

    val mainScheme = when (themesType) {
        ThemesState.FIRST_THEME -> colorScheme
        ThemesState.SECOND_THEME -> secondColorScheme
        ThemesState.THREE_THEME -> threeColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = mainScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    val typography = when (textStyleTypes) {
        TextStyleTypes.SMALL -> TypographySmall
        TextStyleTypes.MEDIUM -> TypographyMedium
        TextStyleTypes.LARGE -> TypographyLarge
    }

    MaterialTheme(
        colorScheme = mainScheme,
        typography = typography,
        content = content
    )
}