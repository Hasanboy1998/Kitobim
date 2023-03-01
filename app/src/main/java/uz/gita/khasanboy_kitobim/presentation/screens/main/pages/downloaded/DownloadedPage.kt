package uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.CustomBookLazyGrid
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.CustomSearchbar
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.HomePageBookLazy
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded.DownloadedContract.*
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomeContract
import uz.gita.khasanboy_kitobim.presentation.viewmodels.DownloadedViewModelImpl

class DownloadedPage : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: DownloadedViewModelImpl = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value
        DownloadedPageContent(uiState = uiState, eventDispatcher = viewModel::onEventDispatchers)
    }
}

@Composable
fun DownloadedPageContent(uiState: UiState, eventDispatcher: (Intent) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var query by remember { mutableStateOf("") }
        val coroutine = rememberCoroutineScope()
        eventDispatcher(Intent.CheckDownloadBook)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            CustomSearchbar(defaultQuery = query, modifier = Modifier.padding(start = 24.dp, end = 24.dp), onValueChange = {
                coroutine.launch {
                    delay(500)
                    query = it.trimStart()
                    eventDispatcher(Intent.SearchBook(it.trimStart()))
                }
            })

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Yuklab olingan kitoblar", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 24.dp))

            Spacer(modifier = Modifier.height(12.dp))
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
                if (uiState.downloadedList.isNotEmpty()) {
                    CustomBookLazyGrid(modifier = Modifier, list = uiState.downloadedList, onClick = { eventDispatcher(Intent.OpenBookDetail(it)) })
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Yuklab olingan kitoblar mavjud emas!", color = Color.Gray)
                    }
                }
            }
        }
    }
}