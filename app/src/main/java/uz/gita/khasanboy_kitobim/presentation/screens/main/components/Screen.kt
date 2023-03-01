package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import uz.gita.khasanboy_kitobim.R

sealed class Screen(val route: String, val icon: Int) {
    object Home : Screen("Asosiy", R.drawable.home_48px)
    object Downloaded : Screen("Yuklanganlar", R.drawable.download_48px)
}