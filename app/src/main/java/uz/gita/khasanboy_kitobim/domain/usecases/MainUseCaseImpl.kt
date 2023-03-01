package uz.gita.khasanboy_kitobim.domain.usecases

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import uz.gita.khasanboy_kitobim.data.UploadData
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.toBookListByCategory
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.toCategoryList
import uz.gita.khasanboy_kitobim.data.remote.responses.GetAllDataResponse
import uz.gita.khasanboy_kitobim.domain.repositories.Repository
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(private val repo: Repository) : MainUseCase {
    override fun getAllBooks(): StateFlow<GetAllDataResponse> = repo.responseBookData

    override fun getAllBooksByCategory(): Flow<List<BaseData.CategoryData>> = flow {
        repo.responseCategoryData.onEach {
            when (it) {
                is GetAllDataResponse.Success -> {
                    val categoryList = it.data.toCategoryList()
                    repo.responseBookData.onEach { subIt ->
                        when (subIt) {
                            is GetAllDataResponse.Success -> {
                                val bookListByCategory = subIt.data.toBookListByCategory(categoryList)
                                emit(bookListByCategory)
                            }
                            is GetAllDataResponse.Error -> {}
                            else -> {}
                        }
                    }.collect()
                }
                is GetAllDataResponse.Error -> {}
                else -> {}
            }
        }.collect()

    }.flowOn(Dispatchers.IO)

    override fun downloadFile(bookNameInStorage: String): Flow<Result<UploadData>> = repo.downloadFile(bookNameInStorage)
    override fun pauseDownload() = repo.pauseDownload()

    override fun resumeDownload() = repo.resumeDownload()

    override fun cancelDownload() = repo.cancelDownload()
}