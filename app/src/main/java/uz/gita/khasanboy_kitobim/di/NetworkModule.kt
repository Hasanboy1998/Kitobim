package uz.gita.khasanboy_kitobim.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun provideFireStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @[Provides Singleton]
    fun provideFireStore(): CollectionReference = Firebase.firestore.collection("books")
}