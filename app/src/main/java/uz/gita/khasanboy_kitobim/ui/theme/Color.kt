package uz.gita.khasanboy_kitobim.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val FirstSurfaceLight = Color(red = 255, green = 251, blue = 254, alpha = 0x6E)
val FirstSurfaceDark = Color(red = 28, green = 27, blue = 31, alpha = 0x6E)

val FirstSurface = if (Theme.isDarkMode.value) FirstSurfaceDark else FirstSurfaceLight


val FirstSurfaceVariantLight = Color(red = 231, green = 224, blue = 236)
val FirstSurfaceVariantDark = Color(red = 73, green = 69, blue = 79)

val FirstSurfaceVariant = if (Theme.isDarkMode.value) FirstSurfaceVariantDark else FirstSurfaceVariantLight

val SearchBgColor = Color(0xFFF3F3F3)
val MainColor = Color(0xFF1B60E6)
val MainBgColor = Color(0xFFEAF4FE)
val UnselectedColor = Color(0xFFCBCBCB)
val Green = Color(0xFF00B407)