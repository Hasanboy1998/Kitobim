package uz.gita.khasanboy_kitobim.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.khasanboy_kitobim.navigation.AppNavigationManager
import uz.gita.khasanboy_kitobim.navigation.AppNavigator
import uz.gita.khasanboy_kitobim.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigator(impl: AppNavigationManager): NavigationHandler

    @Binds
    fun bindNavigationHandler(impl: AppNavigationManager): AppNavigator
}