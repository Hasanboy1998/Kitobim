package uz.gita.khasanboy_kitobim.navigation

import cafe.adriel.voyager.core.screen.Screen


interface AppNavigator {
    suspend fun back()
    suspend fun navigationTo(screen: Screen)
    suspend fun navigationReplace(screen: Screen)
}

