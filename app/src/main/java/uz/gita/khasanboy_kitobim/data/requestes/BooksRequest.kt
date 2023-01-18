package uz.gita.khasanboy_kitobim.data.requestes

data class BooksRequest(
    val author: String,
    val description: String,
    val disLike: Int,
    val image: String,
    val like: Int,
    val name: String,
    val page: Int,
    val bookUrlStorage: String
)