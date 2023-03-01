package uz.gita.khasanboy_kitobim.presentation.screens.pdf_viewer

interface PdfViewerContract {
    interface PdfViewerViewModel {
        fun onEventDispatchers(intent: Intent)
    }

    sealed interface Intent {
        object Back : Intent
    }
}