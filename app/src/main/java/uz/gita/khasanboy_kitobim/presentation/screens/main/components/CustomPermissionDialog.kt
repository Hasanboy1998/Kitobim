package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.gita.khasanboy_kitobim.BuildConfig
import uz.gita.khasanboy_kitobim.ui.theme.Green

@Composable
fun CustomPermissionDialog(setShowDialog: (Boolean) -> Unit) {
    val startForResult = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Eslatma!",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Default,
                                fontStyle = FontStyle.Italic,
                                color = Green
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Kitoblarni telefon xotirasiga yuklab olish va yuklab olingan kitoblarni offlayn holatda o'qish uchun qurilma sozlamalaridan ushbu dasturga ruxsat berishingiz kerak!",
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier) {
                        ElevatedButton(
                            onClick = {
                                setShowDialog(false)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, fill = false),
                        ) {
                            Text(text = "Bekor qilish", fontSize = 11.sp)
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                        ElevatedButton(
                            onClick = {
                                setShowDialog(false)
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                    val intent = android.content.Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                                    intent.addCategory("android.intent.category.DEFAULT")
                                    val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                                    intent.data = uri
                                    startForResult.launch(intent)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, fill = false)
                        ) {
                            Text(text = "Sozlamalar", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}