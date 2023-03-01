package uz.gita.khasanboy_kitobim.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.data.UploadData
import uz.gita.khasanboy_kitobim.data.remote.responses.GetAllDataResponse

interface Repository {
    val responseBookData: StateFlow<GetAllDataResponse>
    val responseCategoryData: StateFlow<GetAllDataResponse>

    fun downloadFile(bookNameInStorage: String): Flow<Result<UploadData>>
    fun pauseDownload()
    fun resumeDownload()
    fun cancelDownload()
}