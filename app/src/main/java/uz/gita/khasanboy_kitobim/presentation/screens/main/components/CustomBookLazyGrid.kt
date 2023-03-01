package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData

@Composable
fun CustomBookLazyGrid(modifier: Modifier, list: List<BaseData.BookData>, onClick: (BaseData.BookData) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(), columns = GridCells.Fixed(2), contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(items = list) { bookData ->
            CustomBookItem(item = bookData, isGrid = true) {
                onClick(bookData)
            }
        }
    }
}