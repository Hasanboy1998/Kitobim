package uz.gita.khasanboy_kitobim.data

import java.io.File

sealed interface UploadData {
    class Complete(val bookNameInStorage: String) : UploadData
    object Success : UploadData
    class Cancel(val bookNameInStorage: String) : UploadData
    object Pause : UploadData
    class Progress(val progress: Int) : UploadData
}


