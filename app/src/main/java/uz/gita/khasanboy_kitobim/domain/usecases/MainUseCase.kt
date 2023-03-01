package uz.gita.khasanboy_kitobim.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.khasanboy_kitobim.data.UploadData
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData
import uz.gita.khasanboy_kitobim.data.remote.responses.GetAllDataResponse

interface MainUseCase {
    fun getAllBooks(): StateFlow<GetAllDataResponse>
    fun getAllBooksByCategory(): Flow<List<BaseData.CategoryData>>
    fun downloadFile(bookNameInStorage: String): Flow<Result<UploadData>>

    fun pauseDownload()
    fun resumeDownload()
    fun cancelDownload()
}