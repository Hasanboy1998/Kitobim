package uz.gita.khasanboy_kitobim.presentation.viewmodels

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.khasanboy_kitobim.data.UploadData
import uz.gita.khasanboy_kitobim.data.stateResponseData.DetailState
import uz.gita.khasanboy_kitobim.data.stateResponseData.DownloaderState
import uz.gita.khasanboy_kitobim.domain.usecases.MainUseCase
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.presentation.screens.detail.DetailContract.*
import uz.gita.khasanboy_kitobim.presentation.screens.pdf_viewer.PdfViewerScreen
import uz.gita.khasanboy_kitobim.utils.Constants
import uz.gita.khasanboy_kitobim.utils.checkFile
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val useCase: MainUseCase
) : DetailViewModel, ViewModel() {
    override val uiState = MutableStateFlow(UiState(0, DetailState.DOWNLOAD))

    override fun onEventDispatchers(intent: Intent) {
        when (intent) {
            Intent.Cancel -> {
                useCase.cancelDownload()
                Log.d("TTT", "IntentCancel")
            }
            is Intent.Download -> {
                useCase.downloadFile(intent.bookData.bookNameInStorage).onEach { result ->
                    result.onSuccess { upload: UploadData ->
                        when (upload) {
                            is UploadData.Progress -> {
                                Log.d("TTT", "SuccProgress")
                                reduce { old -> old.copy(btnState = DetailState.CANCEL, progress = upload.progress) }
                            }

                            is UploadData.Cancel -> {
                                Log.d("TTT", "SuccCancel")
                                val root = File(Environment.getExternalStorageDirectory(), Constants.ROOT_FOLDER)
                                val file = File(root, upload.bookNameInStorage)

                                if (file.exists() && !file.isDirectory)
                                    file.delete()
                                reduce { it.copy(btnState = DetailState.DOWNLOAD, progress = 0) }
                            }

                            UploadData.Success -> {
                                Log.d("TTT", "SuccSucc")
                                reduce { old -> old.copy(btnState = DetailState.OPEN, progress = 0) }
                            }
                            else -> {}
                        }
                    }.onFailure {}
                }.launchIn(viewModelScope)
            }

            is Intent.OpenPdf -> {
                viewModelScope.launch(Dispatchers.IO) {
                    navigator.navigationTo(PdfViewerScreen(intent.filePath))
                }
            }

            is Intent.ButtonState -> {
                when (intent.a) {
                    DetailState.OPEN -> reduce { it.copy(btnState = DetailState.OPEN) }
                    DetailState.DOWNLOAD -> reduce { it.copy(btnState = DetailState.DOWNLOAD) }
                    else -> {}
                }
            }

            is Intent.Back -> {
                viewModelScope.launch {
                    navigator.back()
                }
            }

        }
    }

    override fun initState(bookNameInStorage: String) {
        val stateBtn = checkFile(bookNameInStorage)
        reduce { it.copy(btnState = stateBtn) }
    }

    private fun reduce(block: (UiState) -> UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }
}


