package uz.gita.khasanboy_kitobim.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.khasanboy_kitobim.domain.usecases.MainUseCase
import uz.gita.khasanboy_kitobim.domain.usecases.MainUseCaseImpl


@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsUseCase(impl: MainUseCaseImpl): MainUseCase
}