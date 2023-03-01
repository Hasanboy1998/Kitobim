package uz.gita.khasanboy_kitobim.presentation.screens.main.pages.downloaded

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData

interface DownloadedContract {
    interface DownloadedViewModel{
        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)
    }

    data class UiState(
        val downloadedList: List<BaseData.BookData>,
        val loading: Boolean = false,
        val searchList: SnapshotStateList<BaseData.BookData>
    )

    sealed interface Intent {
        class OpenBookDetail(val bookData: BaseData.BookData) : Intent
        class SearchBook(val query: String): Intent
        object CheckDownloadBook: Intent
    }
}