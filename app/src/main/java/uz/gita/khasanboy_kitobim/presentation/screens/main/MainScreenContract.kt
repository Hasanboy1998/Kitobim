package uz.gita.khasanboy_kitobim.presentation.screens.main

import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.Screen

interface MainScreenContract {
    interface MainScreenViewModel {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }
    data class UiState(
        val screen: Screen
    )

    sealed interface Intent {
        class SelectedNavigation(val screen: Screen): Intent
    }
}