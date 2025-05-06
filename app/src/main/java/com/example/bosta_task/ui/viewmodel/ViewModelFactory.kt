package com.example.bosta_task.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bosta_task.domain.intractor.GetCitiesUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CityViewModel::class.java) -> {
                CityViewModel(getCitiesUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}