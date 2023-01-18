package uz.gita.khasanboy_kitobim.data.mapper

import com.google.firebase.firestore.QueryDocumentSnapshot
import uz.gita.khasanboy_kitobim.data.common.BookData
import uz.gita.khasanboy_kitobim.data.requestes.BooksRequest

object BooksMapper {
    fun BooksRequest.toBookData(bookId: String): BookData =
        BookData(bookId, author, description, disLike, image, like, name, page, bookUrlStorage)

    fun BookData.toBookRequest(): BooksRequest =
        BooksRequest(author, description, disLike, image, like, name, page, bookUrlStorage)

    fun QueryDocumentSnapshot.toBookData(): BookData =
        BookData(
            id = id,
            author = data["author"].toString(),
            description = data["description"].toString(),
            disLike = data["dislike"].toString().toInt(),
            image = data["image-name"].toString(),
            like = data["like"].toString().toInt(),
            name = data["name"].toString(),
            page = data["page"].toString().toInt(),
            bookUrlStorage = data["book-name-in-storage"].toString()
        )
}