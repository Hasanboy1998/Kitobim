package uz.gita.khasanboy_kitobim.presentation.screens.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.gita.khasanboy_kitobim.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Card(onClick = { onClick() }, modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(), shape = RoundedCornerShape(topStart = 24.dp), colors = CardDefaults.cardColors(containerColor = MainColor)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = text, color = Color.White)
        }
    }
}