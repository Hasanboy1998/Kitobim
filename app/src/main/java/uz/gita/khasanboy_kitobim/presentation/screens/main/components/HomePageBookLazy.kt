package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData

@Composable
fun HomePageBookLazy(modifier: Modifier, list: List<BaseData.CategoryData>, onClick: (BaseData.BookData) -> Unit) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = list) { categoryData ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = categoryData.name, fontSize = 20.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                /*Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false))
                Text(text = "Barchasi", color = MainColor, fontSize = 12.sp, modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        *//*TODO Shu kategoriyaga oid barchasi ochilishi kerak*//*
                    })
                })*/
            }

            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 18.dp)) {
                items(items = categoryData.bookList) { bookData ->
                    CustomBookItem(item = bookData, isGrid = false) {
                        onClick(it)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}