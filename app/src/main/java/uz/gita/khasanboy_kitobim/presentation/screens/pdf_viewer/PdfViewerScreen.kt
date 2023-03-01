package uz.gita.khasanboy_kitobim.presentation.screens.pdf_viewer

import android.os.Environment
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.pdfview.PDFView
import uz.gita.khasanboy_kitobim.R.*
import uz.gita.khasanboy_kitobim.presentation.screens.pdf_viewer.PdfViewerContract.*
import uz.gita.khasanboy_kitobim.presentation.viewmodels.PdfViewerViewModelImpl
import uz.gita.khasanboy_kitobim.utils.Constants
import java.io.File

class PdfViewerScreen constructor(private val filePath: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: PdfViewerViewModelImpl = getViewModel()
        PdfViewerContent(filePath = filePath, eventDispatcher = viewModel::onEventDispatchers)
    }
}

@Composable
fun PdfViewerContent(filePath: String, eventDispatcher: (Intent) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val file = remember {
            val root = File(Environment.getExternalStorageDirectory(), Constants.ROOT_FOLDER)
            return@remember File(root, filePath)
        }

        AndroidView(
            factory = { context ->
                View.inflate(context, layout.fragment_pdf_view, null)
            },
            update = { view ->
                Log.d("TTT", "filePath:  ${file.absolutePath}")
                val pdfview = view.findViewById<PDFView>(id.pdfView)
                pdfview.fromFile(file)
                pdfview.show()
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}