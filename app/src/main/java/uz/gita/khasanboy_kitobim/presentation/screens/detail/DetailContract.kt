package uz.gita.khasanboy_kitobim.presentation.screens.detail

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData
import uz.gita.khasanboy_kitobim.data.stateResponseData.DetailState
import uz.gita.khasanboy_kitobim.data.stateResponseData.TextStyleTypes
import uz.gita.khasanboy_kitobim.data.stateResponseData.ThemesState

interface DetailContract {
    interface DetailViewModel {
        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)

        fun initState(bookNameInStorage: String)
    }

    data class UiState(
        val progress: Int,
        val btnState: DetailState
    )

    sealed interface Intent {
        object Cancel : Intent
        object Back : Intent
        class ButtonState(val a: DetailState) : Intent
        class Download(val bookData: BaseData.BookData) : Intent
        class OpenPdf(val filePath: String) : Intent
    }
}