package uz.gita.khasanboy_kitobim.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.presentation.screens.pdf_viewer.PdfViewerContract.*
import javax.inject.Inject

@HiltViewModel
class PdfViewerViewModelImpl @Inject constructor(
    private val navigator: AppNavigator
) : PdfViewerViewModel, ViewModel() {
    override fun onEventDispatchers(intent: Intent) {
        if (intent is Intent.Back) {
            viewModelScope.launch(Dispatchers.IO) {
                navigator.back()
            }
        }
    }
}