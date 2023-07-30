package uz.gita.khasanboy_kitobim

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.gita.khasanboy_kitobim.navigation.NavigationHandler
import uz.gita.khasanboy_kitobim.presentation.screens.main.MainScreen
import uz.gita.khasanboy_kitobim.presentation.screens.splash.SplashScreen
import uz.gita.khasanboy_kitobim.ui.theme.KitobimTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitobimTheme {
                val firstScreen = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) MainScreen() else SplashScreen()

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.White,
                        darkIcons = true
                    )
                }
                Navigator(firstScreen) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.navigationFlow
                            .onEach { it(navigator) }
                            .collect()
                    }
                    CurrentScreen()
                }
            }
        }
    }
}