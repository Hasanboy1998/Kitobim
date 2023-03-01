package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded.DownloadedPage
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomeContract
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomePage
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomePageContent

@Composable
fun CustomNavigationGraph(uiState: HomeContract.UiState, eventDispatcher: (HomeContract.Intent) -> Unit, modifier: Modifier, navController: NavHostController) {
    NavHost(navController, startDestination = "Asosiy", modifier = modifier) {
        composable("Asosiy") {
            HomePageContent(uiState = uiState, eventDispatcher = eventDispatcher)
        }
        composable("Yuklanganlar") {
            DownloadedPage()
        }
        /*composable("Dastur haqida") {
//            AddPostScreen()
        }*/

    }
}