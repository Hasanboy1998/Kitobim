package uz.gita.khasanboy_kitobim.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import uz.gita.khasanboy_kitobim.data.local.shared_prefrences.SharedPref
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.presentation.screens.settings.SettingsContract.*
import uz.gita.khasanboy_kitobim.ui.theme.Theme
import javax.inject.Inject

@HiltViewModel
class SettingsViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val mSharedPref: SharedPref
) : SettingsViewModel, ViewModel() {
    override val uiState = MutableStateFlow(UiState())

    override fun onEventDispatchers(intent: Intent) {
        when (intent) {
            is Intent.SelectTheme -> {
                reduce { old -> old.copy(themesType = intent.themesType) }
                Theme.themeState.value = intent.themesType
                mSharedPref.themes = intent.themesType.key
            }

            is Intent.SelectTextSizeType -> {
                reduce { old -> old.copy(textSizeType = intent.textStyleType) }
                Theme.textStyle.value = intent.textStyleType
                mSharedPref.textStyleType = intent.textStyleType.key
            }

            is Intent.SelectIsDark -> {
                reduce { old -> old.copy(isDark = intent.isDarks) }
                Theme.isDarkMode.value = intent.isDarks
                mSharedPref.isDark = intent.isDarks
            }

            Intent.Back -> {}
        }
    }

    private fun reduce(block: (UiState) -> UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }
}