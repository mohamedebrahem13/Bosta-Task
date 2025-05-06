package com.example.bosta_task.di

import androidx.lifecycle.ViewModelProvider
import com.example.bosta_task.data.repository.CityRepositoryImpl
import com.example.bosta_task.data.repository.remote.CityApi
import com.example.bosta_task.data.repository.remote.RemoteDataSource
import com.example.bosta_task.domain.intractor.GetCitiesUseCase
import com.example.bosta_task.domain.models.repository.ICityRepository
import com.example.bosta_task.domain.models.repository.remote.IRemoteDataSource
import com.example.bosta_task.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideRemoteDataSource(cityApi: CityApi): IRemoteDataSource {
        return RemoteDataSource(cityApi)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideCityRepository(remoteDataSource: IRemoteDataSource): ICityRepository {
        return CityRepositoryImpl(remoteDataSource)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideGetCitiesUseCase(repository: ICityRepository): GetCitiesUseCase {
        return GetCitiesUseCase(repository)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideViewModelFactory(getCitiesUseCase: GetCitiesUseCase): ViewModelProvider.Factory {
        return ViewModelFactory(getCitiesUseCase)
    }
}