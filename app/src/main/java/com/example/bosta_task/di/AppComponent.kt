package com.example.bosta_task.di

import com.example.bosta_task.MainActivity
import com.example.bosta_task.ui.viewmodel.CityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: CityViewModel)
}