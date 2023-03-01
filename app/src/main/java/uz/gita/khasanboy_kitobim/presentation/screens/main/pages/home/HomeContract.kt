package uz.gita.khasanboy_kitobim.presentation.screens.main.pages.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData

interface HomeContract {
    interface HomeViewModel {
        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)
    }

    data class UiState(
        val loading: Boolean,
        val bookListByCategory: List<BaseData.CategoryData>,
        val errorToast: String,
        val searchList: SnapshotStateList<BaseData.BookData>
    )

    sealed interface Intent {
        class OpenBookDetail(val bookData: BaseData.BookData) : Intent
        object OpenSettings : Intent
        class SearchBook(val query: String): Intent
    }
}