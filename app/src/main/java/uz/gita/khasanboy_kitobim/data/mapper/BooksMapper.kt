package uz.gita.khasanboy_kitobim.data.mapper

import android.os.Environment
import com.google.firebase.firestore.QueryDocumentSnapshot
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData

import uz.gita.khasanboy_kitobim.utils.BookFConst
import uz.gita.khasanboy_kitobim.utils.Constants
import java.io.File

object BooksMapper {
    fun QueryDocumentSnapshot.toBookData(): BaseData.BookData =
        BaseData.BookData(
            id = id,
            author = data[BookFConst.AUTHOR].toString(),
            description = data[BookFConst.DESCRIPTION].toString(),
            disLike = data[BookFConst.DISLIKE].toString().toInt(),
            bookImageUrl = data[BookFConst.IMAGE_NAME].toString(),
            like = data[BookFConst.LIKE].toString().toInt(),
            bookName = data[BookFConst.BOOK_NAME].toString(),
            pagesCount = data[BookFConst.PAGES_COUNT].toString().toInt(),
            bookNameInStorage = data[BookFConst.BOOK_NAME_IN_STORAGE].toString(),
            categoryName = data[BookFConst.CATEGORY_NAME].toString(),
            bookUrl = data[BookFConst.BOOK_URL].toString(),
        )

    fun List<BaseData>.toCategoryList(): List<BaseData.CategoryId> {
        val ls = ArrayList<BaseData.CategoryId>()
        this.forEach { baseData: BaseData ->
            if (baseData is BaseData.CategoryId) {
                ls.add(baseData)
            }
        }
        return ls
    }

    fun List<BaseData>.toBookListByCategory(categoryList: List<BaseData.CategoryId>): List<BaseData.CategoryData> {
        val ls = ArrayList<BaseData.CategoryData>()

        categoryList.forEach { categoryId ->
            val bookList = ArrayList<BaseData.BookData>()
            this.toBookList().forEach { bookData ->
                if (bookData.categoryName == categoryId.name) bookList.add(bookData)
            }
            if (bookList.isNotEmpty())
                ls.add(BaseData.CategoryData(name = categoryId.name, bookList = bookList))
        }

        return ls
    }


    fun List<BaseData>.toBookList(): List<BaseData.BookData> {
        val ls = ArrayList<BaseData.BookData>()
        this.forEach { baseData: BaseData ->
            if (baseData is BaseData.BookData) {
                ls.add(baseData)
            }
        }
        return ls
    }

    fun List<BaseData>.downloadedBooks(): List<BaseData.BookData> {
        val ls = ArrayList<BaseData.BookData>()
        val root = File(Environment.getExternalStorageDirectory(), Constants.ROOT_FOLDER)
        this.forEach { baseData: BaseData ->
            if (baseData is BaseData.BookData) {
                if (File(root, baseData.bookNameInStorage).exists()) {
                    ls.add(baseData)
                }
            }
        }
        return ls
    }

    fun List<BaseData>.searchBookData(query: String): List<BaseData.BookData> {
        val ls = ArrayList<BaseData.BookData>()
        this.forEach { baseData ->
            when (baseData) {
                is BaseData.CategoryData -> {
                    baseData.bookList.forEach { bookData ->
                        if (bookData.bookName.contains(query, ignoreCase = true)) ls.add(bookData)
                    }
                }
                is BaseData.BookData -> {
                    if (baseData.bookName.contains(query, ignoreCase = true)) ls.add(baseData)
                }
                else -> {}
            }
        }
        return ls
    }


}