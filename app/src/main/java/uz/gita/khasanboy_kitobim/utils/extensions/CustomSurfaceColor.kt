package uz.gita.khasanboy_kitobim.utils.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

@Composable
fun customSurfaceColor(backgroundColor: Color, surfaceVariant: Color) =
    MaterialTheme.colorScheme.contentColorFor(backgroundColor).takeOrElse {
        surfaceVariant
    }