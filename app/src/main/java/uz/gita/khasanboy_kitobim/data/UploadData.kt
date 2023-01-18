package uz.gita.khasanboy_kitobim.data

import java.io.File

sealed interface UploadData {
    object Complete : UploadData
    class Success(file: File) : UploadData
    object Cancel : UploadData
    object Pause: UploadData
    class Progress(val progress: Int) : UploadData
}


