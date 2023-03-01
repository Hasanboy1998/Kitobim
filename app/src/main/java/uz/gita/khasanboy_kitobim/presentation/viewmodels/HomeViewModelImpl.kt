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
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.searchBookData
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.toBookList
import uz.gita.khasanboy_kitobim.data.remote.responses.GetAllDataResponse
import uz.gita.khasanboy_kitobim.domain.usecases.MainUseCase
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.presentation.screens.detail.DetailScreen
import uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home.HomeContract
import uz.gita.khasanboy_kitobim.presentation.screens.settings.SettingScreen
import javax.inject.Inject


@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    useCase: MainUseCase,
    private val navigator: AppNavigator
) : HomeContract.HomeViewModel, ViewModel() {
    override val uiState = MutableStateFlow(HomeContract.UiState(false, emptyList(), errorToast = "", searchList = SnapshotStateList()))

    init {
        reduce { it.copy(loading = true) }
        useCase.getAllBooksByCategory().onEach {
            reduce { old -> old.copy(loading = false, bookListByCategory = it) }
        }.launchIn(viewModelScope)
    }

    override fun onEventDispatchers(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.OpenBookDetail -> {
                viewModelScope.launch(Dispatchers.IO) {
                    navigator.navigationTo(DetailScreen(intent.bookData))
                }
            }
            HomeContract.Intent.OpenSettings -> {
                viewModelScope.launch(Dispatchers.IO) {
                    navigator.navigationTo(SettingScreen())
                }
            }
            is HomeContract.Intent.SearchBook -> {
                uiState.value.searchList.clear()
                if (intent.query.isNotEmpty()) {
                    val allBook = uiState.value.bookListByCategory.searchBookData(intent.query)
                    uiState.value.searchList.addAll(allBook)
                }
            }
        }
    }

    private fun reduce(block: (HomeContract.UiState) -> HomeContract.UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }
}