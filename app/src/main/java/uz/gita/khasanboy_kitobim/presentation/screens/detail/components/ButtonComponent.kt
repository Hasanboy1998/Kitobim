package uz.gita.khasanboy_kitobim.presentation.screens.detail.components

import android.util.Log
import androidx.compose.runtime.*
import uz.gita.khasanboy_kitobim.data.stateResponseData.DetailState
import uz.gita.khasanboy_kitobim.presentation.screens.detail.DetailContract
import uz.gita.khasanboy_kitobim.utils.ComposePermission

@Composable
fun ButtonComponent(uiState: DetailContract.UiState, onClickDownload: () -> Unit, onClickCancel: () -> Unit, onClickOpen: () -> Unit) {
    var isShowPermission by remember { mutableStateOf(false) }

    when (uiState.btnState) {
        DetailState.DOWNLOAD -> {
            CustomButton(text = "Yuklash") {
                isShowPermission = true
            }
        }
        DetailState.CANCEL -> {
            CustomButton(text = "Bekor qilish") {
                onClickCancel()
            }
        }
        DetailState.OPEN -> {
            CustomButton(text = "O'qish") {
                onClickOpen()
            }
        }
    }

    if (isShowPermission) {
        ComposePermission {
            if (it) {
                Log.d("TTT", "Downloadddd")
                onClickDownload()
            }
            isShowPermission = false
        }
    }
}