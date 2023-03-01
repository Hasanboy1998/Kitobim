package uz.gita.khasanboy_kitobim.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.presentation.screens.main.MainScreenContract
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.Screen
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor() : MainScreenContract.MainScreenViewModel, ViewModel() {
    override val uiState = MutableStateFlow(MainScreenContract.UiState(Screen.Home))

    override fun onEventDispatcher(intent: MainScreenContract.Intent) {
        if (intent is MainScreenContract.Intent.SelectedNavigation) {
            val old = uiState.value
            uiState.value = old.copy(screen = intent.screen)
        }
    }
}