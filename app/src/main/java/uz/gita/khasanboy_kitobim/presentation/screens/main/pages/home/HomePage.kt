package uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.CustomBookLazyGrid
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.CustomSearchbar
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.HomePageBookLazy
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomeContract.*
import uz.gita.khasanboy_kitobim.presentation.viewmodels.HomeViewModelImpl

class HomePage : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModelImpl = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value
        HomePageContent(uiState = uiState, eventDispatcher = viewModel::onEventDispatchers)
    }
}

@Composable
fun HomePageContent(uiState: UiState, eventDispatcher: (Intent) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var query by remember { mutableStateOf("") }
        val coroutine = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(24.dp))
            CustomSearchbar(defaultQuery = query, modifier = Modifier.padding(start = 24.dp, end = 24.dp), onValueChange = {
                coroutine.launch {
                    delay(500)
                    query = it.trimStart()
                    eventDispatcher(Intent.SearchBook(it.trimStart()))
                }
            })

            Spacer(modifier = Modifier.height(24.dp))
            if (query.isNotEmpty()) {
                if (uiState.searchList.isNotEmpty()) {
                    CustomBookLazyGrid(modifier = Modifier, list = uiState.searchList) {
                        query = ""
                        eventDispatcher(Intent.OpenBookDetail(it))
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Kitob topilmadi!", color = Color.Gray)
                    }
                }
            } else {
                HomePageBookLazy(modifier = Modifier, list = uiState.bookListByCategory) {
                    eventDispatcher(Intent.OpenBookDetail(it))
                }
            }
        }
    }
}