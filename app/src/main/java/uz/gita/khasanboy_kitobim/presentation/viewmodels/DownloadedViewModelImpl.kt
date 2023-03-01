package uz.gita.khasanboy_kitobim.presentation.viewmodels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.downloadedBooks
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.searchBookData
import uz.gita.khasanboy_kitobim.data.remote.responses.GetAllDataResponse
import uz.gita.khasanboy_kitobim.domain.usecases.MainUseCase
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.presentation.screens.detail.DetailScreen
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded.DownloadedContract
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomeContract
import javax.inject.Inject

@HiltViewModel
class DownloadedViewModelImpl @Inject constructor(
    private val useCase: MainUseCase,
    private val navigator: AppNavigator
) : DownloadedContract.DownloadedViewModel, ViewModel() {
    override val uiState = MutableStateFlow(DownloadedContract.UiState(downloadedList = emptyList(), searchList = SnapshotStateList()))

    override fun onEventDispatchers(intent: DownloadedContract.Intent) {
        when (intent) {
            is DownloadedContract.Intent.OpenBookDetail -> {
                viewModelScope.launch(Dispatchers.IO) {
                    navigator.navigationTo(DetailScreen(intent.bookData))
                }
            }
            is DownloadedContract.Intent.SearchBook -> {
                uiState.value.searchList.clear()
                if (intent.query.isNotEmpty()) {
                    val allBook = uiState.value.downloadedList.searchBookData(intent.query)
                    uiState.value.searchList.addAll(allBook)
                }
            }
            DownloadedContract.Intent.CheckDownloadBook -> {
                useCase.getAllBooks().onEach {
                    when (it) {
                        is GetAllDataResponse.Success -> {
                            reduce { old -> old.copy(loading = false, downloadedList = it.data.downloadedBooks()) }

                        }
                        is GetAllDataResponse.Error -> {reduce { old -> old.copy(loading = false) }}
                        else -> {}
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun reduce(block: (DownloadedContract.UiState) -> DownloadedContract.UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }
}