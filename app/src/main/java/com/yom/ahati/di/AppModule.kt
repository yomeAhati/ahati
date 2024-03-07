package com.yom.ahati.di

import com.yom.ahati.data.VideosRepository
import com.yom.ahati.view_model.VideosViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun ProvideVideosRepository() : VideosRepository{
        return VideosRepository()
    }
//    @Provides
//    fun provideVideosViewModel(videosRepository: VideosRepository): VideosViewModel {
//        return VideosViewModel(videosRepository)
//    }
}