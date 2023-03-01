@file:OptIn(ExperimentalGlideComposeApi::class)

package uz.gita.khasanboy_kitobim.presentation.screens.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uz.gita.khasanboy_kitobim.R
import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData
import uz.gita.khasanboy_kitobim.data.stateResponseData.DetailState
import uz.gita.khasanboy_kitobim.presentation.screens.detail.components.ButtonComponent
import uz.gita.khasanboy_kitobim.presentation.viewmodels.DetailViewModelImpl
import uz.gita.khasanboy_kitobim.ui.theme.MainBgColor
import uz.gita.khasanboy_kitobim.ui.theme.MainColor

class DetailScreen(private val bookData: BaseData.BookData) : AndroidScreen() {
    @SuppressLint("RememberReturnType")
    @Composable
    override fun Content() {
        val viewModel: DetailViewModelImpl = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value
        remember { viewModel.initState(bookData.bookNameInStorage) }

        DetailScreenContent(bookData = bookData, uiState = uiState, eventDispatcher = viewModel::onEventDispatchers)
    }
}

@Composable
fun DetailScreenContent(bookData: BaseData.BookData, uiState: DetailContract.UiState, eventDispatcher: (DetailContract.Intent) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .align(Alignment.TopCenter)
                    .background(color = Color.Black)
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    model = bookData.bookImageUrl,
                    contentDescription = bookData.bookName,
                    contentScale = ContentScale.Crop
                ) {
                    it.placeholder(R.drawable.place_holder)
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .align(Alignment.BottomCenter), color = Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, fill = false)
                        ) {
                            Text(text = bookData.bookName, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        }

                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier
                                .background(color = MainBgColor, shape = CircleShape)
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Bepul", color = MainColor, modifier = Modifier.padding(vertical = 2.dp),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                    }
                    if (bookData.author != "") {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        ) {
                            Text(text = "Muallif: ", color = Color.Gray, fontSize = 12.sp)
                            Text(text = bookData.author, color = Color.Gray, fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Kitob haqida", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 24.dp))

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = bookData.description, color = Color.Gray, overflow = TextOverflow.Ellipsis, fontSize = 12.sp, modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                            .weight(1f, fill = false)
                    )

                    if (uiState.btnState == DetailState.CANCEL) {
                        Box(
                            modifier = Modifier
                                .height(8.dp)
                                .padding(horizontal = 24.dp)
                                .background(color = Color.Transparent, shape = CircleShape), contentAlignment = Alignment.Center
                        ) {
                            Log.d("TTT", "${uiState.progress}")
                            LinearProgressIndicator(progress = (uiState.progress.toFloat() / 100), modifier = Modifier.fillMaxWidth())
                        }
                    } else {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp), contentAlignment = Alignment.CenterEnd
                    ) {
                        ButtonComponent(uiState = uiState,
                            onClickDownload = {
                                eventDispatcher(DetailContract.Intent.Download(bookData))
                            },
                            onClickCancel = {
                                eventDispatcher(DetailContract.Intent.Cancel)
                            },
                            onClickOpen = {
                                eventDispatcher(DetailContract.Intent.OpenPdf(bookData.bookNameInStorage))
                            })
                    }
                }
            }

            FloatingActionButton(
                onClick = { eventDispatcher(DetailContract.Intent.Back) },
                modifier = Modifier
                    .padding(24.dp)
                    .size(42.dp),
                containerColor = Color.White.copy(alpha = 0.25f),
                elevation = FloatingActionButtonDefaults.elevation(0.dp, pressedElevation = 0.dp),
                shape = CircleShape
            ) {
                Icon(tint = Color.White, imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "", modifier = Modifier.fillMaxSize())
            }
        }
    }
}