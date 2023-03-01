@file:OptIn(ExperimentalMaterial3Api::class)

package uz.gita.khasanboy_kitobim.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.*
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded.DownloadedContract
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded.DownloadedPage
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded.DownloadedPageContent
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomeContract
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomePage
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomePageContent
import uz.gita.khasanboy_kitobim.presentation.viewmodels.DownloadedViewModelImpl
import uz.gita.khasanboy_kitobim.presentation.viewmodels.HomeViewModelImpl
import uz.gita.khasanboy_kitobim.presentation.viewmodels.MainScreenViewModelImpl
import uz.gita.khasanboy_kitobim.utils.ConnectionState
import uz.gita.khasanboy_kitobim.utils.connectivityState

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModelHome: HomeViewModelImpl = getViewModel()
        val uiStateHome = viewModelHome.uiState.collectAsState().value

        val viewModelDownload: DownloadedViewModelImpl = getViewModel()
        val uiStateDownload = viewModelDownload.uiState.collectAsState().value

        val viewModelScreen: MainScreenViewModelImpl = getViewModel()
        val uiStateScreen = viewModelScreen.uiState.collectAsState().value

        MainScreenContent(
            uiStateScreen = uiStateScreen,
            eventDispatcherScreen = viewModelScreen::onEventDispatcher,
            uiStateHome = uiStateHome,
            eventDispatcherHome = viewModelHome::onEventDispatchers,
            uiStateDownload = uiStateDownload,
            eventDispatcherDownload = viewModelDownload::onEventDispatchers
        )
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreenContent(
    uiStateScreen: MainScreenContract.UiState,
    eventDispatcherScreen: (MainScreenContract.Intent) -> Unit,
    uiStateHome: HomeContract.UiState,
    eventDispatcherHome: (HomeContract.Intent) -> Unit,
    uiStateDownload: DownloadedContract.UiState,
    eventDispatcherDownload: (DownloadedContract.Intent) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            val connection by connectivityState()
            val isConnectedNetwork = connection === ConnectionState.Available
            var oldStateNetwork by remember { mutableStateOf(true) }
            var visibleConttection by remember { mutableStateOf(false) }

            if (!isConnectedNetwork) {
                visibleConttection = true
                oldStateNetwork = isConnectedNetwork
            }

            LaunchedEffect(key1 = isConnectedNetwork, block = {
                if (!oldStateNetwork) {
                    if (isConnectedNetwork) {
                        visibleConttection = true
                        delay(3000)
                        visibleConttection = false
                    }
                }
            })

            Scaffold(modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            icon = { Icon(painter = painterResource(id = Screen.Home.icon), contentDescription = Screen.Home.route) },
                            label = { Text(text = Screen.Home.route, fontSize = 12.sp, fontWeight = FontWeight.W500) },
                            selected = Screen.Home == uiStateScreen.screen,
                            alwaysShowLabel = Screen.Home == uiStateScreen.screen,
                            onClick = { eventDispatcherScreen(MainScreenContract.Intent.SelectedNavigation(Screen.Home)) }
                        )
                        NavigationBarItem(
                            icon = { Icon(painter = painterResource(id = Screen.Downloaded.icon), contentDescription = Screen.Downloaded.route) },
                            label = { Text(text = Screen.Downloaded.route, fontSize = 12.sp, fontWeight = FontWeight.W500) },
                            selected = Screen.Downloaded == uiStateScreen.screen,
                            alwaysShowLabel = Screen.Downloaded == uiStateScreen.screen,
                            onClick = { eventDispatcherScreen(MainScreenContract.Intent.SelectedNavigation(Screen.Downloaded)) }
                        )

                    }
                },
                content = {
                    Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                        when (uiStateScreen.screen) {
                            Screen.Home -> HomePageContent(uiState = uiStateHome, eventDispatcher = eventDispatcherHome)
                            Screen.Downloaded -> DownloadedPageContent(uiState = uiStateDownload, eventDispatcher = eventDispatcherDownload)
                        }
                    }
                })
            if (visibleConttection) {
                CustomNetworkState(connected = isConnectedNetwork)
            }
        }
    }
}