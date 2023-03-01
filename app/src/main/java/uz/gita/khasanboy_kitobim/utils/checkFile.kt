package uz.gita.khasanboy_kitobim.utils

import android.os.Environment
import uz.gita.khasanboy_kitobim.data.stateResponseData.DetailState
import java.io.File

fun checkFile(bookNameInStorage: String): DetailState {
    val rootFile = File(Environment.getExternalStorageDirectory(), Constants.ROOT_FOLDER)
    val file = File(rootFile, bookNameInStorage)
    return if (file.exists() && !file.isDirectory) {
        DetailState.OPEN
    } else {
        DetailState.DOWNLOAD
    }
}