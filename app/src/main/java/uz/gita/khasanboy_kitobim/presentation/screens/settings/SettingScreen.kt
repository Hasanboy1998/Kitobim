package uz.gita.khasanboy_kitobim.presentation.screens.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.khasanboy_kitobim.data.stateResponseData.TextStyleTypes.*
import uz.gita.khasanboy_kitobim.data.stateResponseData.ThemesState
import uz.gita.khasanboy_kitobim.presentation.screens.settings.SettingsContract.*
import uz.gita.khasanboy_kitobim.presentation.viewmodels.SettingsViewModelImpl
import uz.gita.khasanboy_kitobim.ui.theme.*

class SettingScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SettingsViewModelImpl = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value
        SettingScreenContent(uiState = uiState, eventDispatcher = viewModel::onEventDispatchers)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreenContent(uiState: UiState, eventDispatcher: (Intent) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))) {
            val size by remember {
                mutableStateOf(
                    when (uiState.textSizeType) {
                        SMALL -> TypographySmall.bodyLarge
                        MEDIUM -> TypographyMedium.bodyLarge
                        else -> TypographyLarge.bodyLarge
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Mavzular", style = size, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight(0.205f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(
                    items = listOf(
                        ThemeModel(FirstSurface, FirstSurfaceVariant, FirstSurfaceDark, FirstSurfaceVariantDark),
                        ThemeModel(SecondSurface, SecondSurfaceVariant, SecondSurfaceDark, SecondSurfaceVariantDark),
                        ThemeModel(ThreeSurface, ThreeSurfaceVariant, ThreeSurfaceDark, ThreeSurfaceVariantDark),
                    )
                ) { index, theme ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .aspectRatio(0.73f),
                            colors = CardDefaults.cardColors(if (Theme.isDarkMode.value) theme.surfaceVariantDark else theme.surfaceVariantLight),
                            elevation = CardDefaults.cardElevation(5.dp)
                        ) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.93f),
                                color = if (Theme.isDarkMode.value) theme.surfaceDark else theme.surfaceLight,
                            ) {
                            }
                            RadioButton(
                                selected = (uiState.themesType.key == index + 1),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.07f),
                                onClick = {
                                    when (index + 1) {
                                        1 -> {
                                            eventDispatcher(Intent.SelectTheme(ThemesState.FIRST_THEME))
                                        }
                                        2 -> {
                                            eventDispatcher(Intent.SelectTheme(ThemesState.SECOND_THEME))
                                        }
                                        3 -> {
                                            eventDispatcher(Intent.SelectTheme(ThemesState.THREE_THEME))
                                        }
                                    }
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Yozuv o'lchamlari", style = size, modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "AaBbCc", modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp), textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.CenterStart), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Kichik", style = TypographySmall.bodyLarge)
                    RadioButton(
                        selected = (uiState.textSizeType.key == 1),
                        onClick = { eventDispatcher(Intent.SelectTextSizeType(SMALL)) })
                }
                Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "O'rta", style = TypographyMedium.bodyLarge)
                    RadioButton(
                        selected = (uiState.textSizeType.key == 2),
                        onClick = { eventDispatcher(Intent.SelectTextSizeType(MEDIUM)) })
                }
                Column(modifier = Modifier.align(Alignment.CenterEnd), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Katta", style = TypographyLarge.bodyLarge)
                    RadioButton(
                        selected = (uiState.textSizeType.key == 3),
                        onClick = { eventDispatcher(Intent.SelectTextSizeType(LARGE)) })
                }

            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Rejim", style = size, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight(0.41f)
                    .align(CenterHorizontally),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                items(1) {
                    val model = getSurface()
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .aspectRatio(0.73f),
                        colors = CardDefaults.cardColors(model.surfaceVariantLight),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.93f),
                            color = model.surfaceLight,
                        ) {}
                        RadioButton(
                            colors = RadioButtonDefaults.colors(unselectedColor = md_theme_light_onSurface),
                            selected = (!Theme.isDarkMode.value),
                            onClick = { eventDispatcher(Intent.SelectIsDark(false)) }, modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .aspectRatio(0.73f),
                        colors = CardDefaults.cardColors(model.surfaceVariantDark),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.93f),
                            color = model.surfaceDark,
                        ) { }
                        RadioButton(
                            colors = RadioButtonDefaults.colors(unselectedColor = md_theme_dark_onSurface),
                            selected = (Theme.isDarkMode.value),
                            onClick = { eventDispatcher(Intent.SelectIsDark(true)) }, modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

private fun getSurface(): ThemeModel {
    val model = when (Theme.themeState.value) {
        ThemesState.FIRST_THEME -> ThemeModel(FirstSurfaceLight, FirstSurfaceVariantLight, FirstSurfaceDark, FirstSurfaceVariantDark)
        ThemesState.SECOND_THEME -> ThemeModel(
            SecondSurfaceLight,
            SecondSurfaceVariantLight,
            SecondSurfaceDark,
            SecondSurfaceVariantDark
        )
        ThemesState.THREE_THEME -> ThemeModel(ThreeSurfaceLight, ThreeSurfaceVariantLight, ThreeSurfaceDark, ThreeSurfaceVariantDark)
    }
    return model
}

private data class ThemeModel(
    val surfaceLight: Color,
    val surfaceVariantLight: Color,
    val surfaceDark: Color = surfaceLight,
    val surfaceVariantDark: Color = surfaceVariantLight
)
