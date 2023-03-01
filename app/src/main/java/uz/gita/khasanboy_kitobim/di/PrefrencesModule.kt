package uz.gita.khasanboy_kitobim.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
class PreferencesModule {
    @[Provides]
    fun provideShared(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("BOOK_APP", Context.MODE_PRIVATE)
}