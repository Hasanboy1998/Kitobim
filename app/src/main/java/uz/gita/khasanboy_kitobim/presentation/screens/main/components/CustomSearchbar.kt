package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.khasanboy_kitobim.ui.theme.KitobimTheme
import uz.gita.khasanboy_kitobim.ui.theme.SearchBgColor

@Composable
fun CustomSearchbar(defaultQuery: String, modifier: Modifier, onValueChange: (String) -> Unit) {
    var query by rememberSaveable { mutableStateOf(defaultQuery) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (defaultQuery == "") {
        onValueChange("")
        query = ""
    }

    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            if (keypadHeight < screenHeight * 0.15) {
                focusManager.clearFocus()
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color.Transparent), shape = RoundedCornerShape(15.dp), color = SearchBgColor
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                value = query,
                placeholder = {
                    Text(text = "Sevimli kitobingizni qidiring...", color = Color.Gray, overflow = TextOverflow.Ellipsis)
                },
                trailingIcon = {
                    if (query.isNotEmpty())
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Tozalash", modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures(onPress = {
                                query = ""
                                onValueChange("")
                                focusManager.clearFocus()
                            })
                        })
                    else
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Qidirish")
                },
                singleLine = true,
                onValueChange = {
                    query = it
                    onValueChange(it)
                    if (it == "") {
                        focusRequester.requestFocus()
                    }
                })
        }
    }
}