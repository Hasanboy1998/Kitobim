package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
inline fun CustomScaffold(modifier: Modifier, bottomBar: @Composable ColumnScope.() -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = modifier) {
        Column(modifier = modifier.weight(1f, fill = false)) {
            content()
        }
        Column {
            bottomBar()
        }
    }
}