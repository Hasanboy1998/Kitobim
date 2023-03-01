package uz.gita.khasanboy_kitobim.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import uz.gita.khasanboy_kitobim.presentation.screens.main.components.CustomPermissionDialog

@Composable
fun ComposePermission(download: (Boolean) -> Unit) {
    val context = LocalContext.current
    val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val launcherMultiplePermissions = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
        var isShowDialog by remember { mutableStateOf(!Environment.isExternalStorageManager()) }
        if (isShowDialog) {
            CustomPermissionDialog(setShowDialog = { /*isShowDialog = it*/
                download(false)
            })
        }
    } else {
        if (permissions.all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }) {
            download(true)
        } else {
            SideEffect {
                download(false)
                launcherMultiplePermissions.launch(permissions)
            }
        }
    }

}