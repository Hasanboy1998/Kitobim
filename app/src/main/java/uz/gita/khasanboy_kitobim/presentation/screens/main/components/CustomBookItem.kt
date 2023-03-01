package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uz.gita.khasanboy_kitobim.R
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData
import uz.gita.khasanboy_kitobim.ui.theme.MainBgColor
import uz.gita.khasanboy_kitobim.ui.theme.MainColor

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomBookItem(isGrid: Boolean, item: BaseData.BookData, onClick: (BaseData.BookData) -> Unit) {
    Card(
        onClick = {
            onClick(item)
        },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = if (isGrid) {
            Modifier.fillMaxSize()
        } else {
            Modifier.width(153.dp)
        }
    ) {
        Column(modifier = Modifier.padding(6.dp)) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
                /*.height(201.dp)*/, shape = RoundedCornerShape(15.dp)
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    model = item.bookImageUrl,
                    contentDescription = item.bookName,
                    contentScale = ContentScale.Crop
                ) {
                    it.placeholder(R.drawable.place_holder)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.bookName, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .background(color = MainBgColor, shape = CircleShape)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Bepul", color = MainColor, modifier = Modifier.padding(vertical = 2.dp),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}