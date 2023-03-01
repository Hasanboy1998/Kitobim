package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.gita.khasanboy_kitobim.R
import uz.gita.khasanboy_kitobim.ui.theme.Green

@Composable
fun CustomNetworkState(connected: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (connected) Green else Color.Red).padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = if (connected) R.drawable.connected else R.drawable.un_connected), contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (connected) "Internet mavjud" else "Internet mavjud emas", color = Color.White)
    }
}