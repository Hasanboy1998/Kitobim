package uz.gita.khasanboy_kitobim.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.hasanboy_suvhaqidaeslatma.presentation.screens.splash.SplashViewModel
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.presentation.screens.splash.SplashScreen
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val navigator: AppNavigator) : SplashViewModel, ViewModel() {
    init {
        viewModelScope.launch {
            delay(2000)
            navigator.navigationReplace(/*TODO MainScreen()*/SplashScreen())
        }
    }
}