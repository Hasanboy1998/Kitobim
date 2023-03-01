package uz.gita.khasanboy_kitobim.data.stateResponseData

sealed interface BaseData {
    data class CategoryData(val name: String, val bookList: List<BookData>): BaseData
    data class CategoryId(val name: String): BaseData
    data class BookData(
        val id: String,
        val author: String,
        val description: String,
        val disLike: Int,
        val bookImageUrl: String,
        val like: Int,
        val bookName: String,
        val pagesCount: Int,
        val bookNameInStorage: String,
        val categoryName: String,
        val bookUrl: String
    ): java.io.Serializable, BaseData
}