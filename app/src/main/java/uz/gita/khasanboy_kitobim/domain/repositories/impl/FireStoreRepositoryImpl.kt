package uz.gita.khasanboy_kitobim.domain.repositories.impl

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.khasanboy_kitobim.data.UploadData
import uz.gita.khasanboy_kitobim.data.common.BookData
import uz.gita.khasanboy_kitobim.data.mapper.BooksMapper.toBookData
import uz.gita.khasanboy_kitobim.domain.repositories.FireStoreRepository
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val database: CollectionReference
) :
    FireStoreRepository {

    private var snapshotListener: ListenerRegistration? = null

    init {
        snapshotListener = database
            .addSnapshotListener { value, error ->
                val list = ArrayList<BookData>()
                value?.forEach { document ->
                    list.add(
                        document.toBookData()
                    )
                }
                dataFlow.tryEmit(list)
                value?.let { errorFlow.tryEmit("it") }
            }
    }

    override fun downloadFile(bookNameStorage: String): Flow<Result<UploadData>> = callbackFlow {

    }

    override fun pauseDownload() {

    }

    override fun resumeDownload() {

    }

    override fun cancelDownload() {

    }
}