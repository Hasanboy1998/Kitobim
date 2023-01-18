package uz.gita.khasanboy_kitobim.domain.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.khasanboy_kitobim.data.UploadData

interface FireStoreRepository {
    fun downloadFile(bookNameStorage: String): Flow<Result<UploadData>>

    fun pauseDownload()
    fun resumeDownload()
    fun cancelDownload()
}