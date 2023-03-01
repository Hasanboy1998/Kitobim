package uz.gita.khasanboy_kitobim.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.khasanboy_kitobim.domain.repositories.Repository
import uz.gita.khasanboy_kitobim.domain.repositories.impl.RepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
    @Binds
    fun bindFireRepo(impl: RepositoryImpl): Repository
}