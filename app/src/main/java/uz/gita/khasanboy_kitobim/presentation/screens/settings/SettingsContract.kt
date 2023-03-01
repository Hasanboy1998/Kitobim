package uz.gita.khasanboy_kitobim.presentation.screens.settings

import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.data.stateResponseData.TextStyleTypes
import uz.gita.khasanboy_kitobim.data.stateResponseData.ThemesState
import uz.gita.khasanboy_kitobim.ui.theme.Theme

class SettingsContract {
    interface SettingsViewModel {
        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)
    }

    data class UiState(
        val themesType: ThemesState = Theme.themeState.value,
        val textSizeType: TextStyleTypes = Theme.textStyle.value,
        val isDark: Boolean = Theme.isDarkMode.value
    )

    sealed interface Intent {
        object Back : Intent
        class SelectTheme(val themesType: ThemesState) : Intent
        class SelectTextSizeType(val textStyleType: TextStyleTypes) : Intent
        class SelectIsDark(val isDarks: Boolean) : Intent
    }
}