package uz.gita.khasanboy_kitobim.domain.repositories.impl

import android.content.Context
import android.os.Environment
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import uz.gita.khasanboy_kitobim.data.UploadData
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.toBookData
import uz.gita.khasanboy_kitobim.data.remote.responses.GetAllDataResponse
import uz.gita.khasanboy_kitobim.domain.repositories.Repository
import uz.gita.khasanboy_kitobim.utils.Constants
import java.io.File
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storage: FirebaseStorage,
    fireDatabase: FirebaseFirestore,
) : Repository {

    private var snapshotAllBookListener: ListenerRegistration? = null
    private var snapshotCategoryListener: ListenerRegistration? = null
    private lateinit var downTask: StorageTask<FileDownloadTask.TaskSnapshot>
    override val responseBookData = MutableStateFlow<GetAllDataResponse>(GetAllDataResponse.Loading)
    override val responseCategoryData = MutableStateFlow<GetAllDataResponse>(GetAllDataResponse.Loading)
    private val errorFlow = MutableStateFlow<String?>(null)

    init {
        snapshotCategoryListener = fireDatabase.collection("Categories")
            .addSnapshotListener { value, error ->
                val list = ArrayList<BaseData.CategoryId>()
                value?.forEach { document ->
                    list.add(
                        BaseData.CategoryId(document.id)
                    )
                }
                responseCategoryData.tryEmit(GetAllDataResponse.Success(list))
                value.let { errorFlow.tryEmit("it") }
            }

        snapshotAllBookListener = fireDatabase.collection("books")
            .addSnapshotListener { value, error ->
                val list = ArrayList<BaseData.BookData>()
                value?.forEach { document ->
                    list.add(
                        document.toBookData()
                    )
                }
                responseBookData.tryEmit(GetAllDataResponse.Success(list))
            }
    }

    override fun downloadFile(bookNameInStorage: String): Flow<Result<UploadData>> = callbackFlow<Result<UploadData>> {
        val ref = storage.reference.child("books/$bookNameInStorage")
        val rootFile = File(Environment.getExternalStorageDirectory(), Constants.ROOT_FOLDER)
        if (!rootFile.exists()) {
            rootFile.mkdirs()
        }
        val temp = File(rootFile, bookNameInStorage)


        downTask = ref.getFile(temp)
            .addOnCompleteListener {
                trySend(Result.success(UploadData.Complete(bookNameInStorage)))
            }
            .addOnSuccessListener {
                trySend(Result.success(UploadData.Success))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
            .addOnProgressListener {
                val progress = it.bytesTransferred * 100 / it.totalByteCount
                trySend(Result.success(UploadData.Progress(progress.toInt())))
            }
            .addOnCanceledListener {
                trySend(Result.success(UploadData.Cancel(bookNameInStorage)))
            }
            .addOnPausedListener {
                trySend(Result.success(UploadData.Pause))
            }
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun pauseDownload() {
        if (::downTask.isInitialized)
            downTask.pause()
    }

    override fun resumeDownload() {
        if (::downTask.isInitialized)
            downTask.resume()
    }

    override fun cancelDownload() {
        if (::downTask.isInitialized)
            downTask.cancel()
    }
}