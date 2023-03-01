package uz.gita.khasanboy_kitobim.presentation.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.khasanboy_kitobim.presentation.viewmodels.SplashViewModelImpl
import uz.gita.khasanboy_kitobim.ui.theme.KitobimTheme

class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SplashViewModelImpl = getViewModel()
//        val uiState = viewModel.uiState.collectAsState().value
//        KitobimTheme(darkTheme = uiState.isDark, themesType = uiState.themesType) {
        SplashContent()
//        }
    }
}

@Composable
fun SplashContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Splash", fontSize = 32.sp)
        }
    }
}