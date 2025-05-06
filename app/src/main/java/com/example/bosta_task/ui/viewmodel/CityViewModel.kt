package com.example.bosta_task.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosta_task.common.Resource
import com.example.bosta_task.domain.intractor.GetCitiesUseCase
import com.example.bosta_task.domain.models.CitiesResponse
import com.example.bosta_task.domain.models.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _citiesResponse = MutableStateFlow<Resource<CitiesResponse>>(Resource.Progress(loading = false))

    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities

    private var fullCityList: List<City> = emptyList()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getCities(countryId: String) {
        _citiesResponse.value = Resource.Progress(loading = true)

        viewModelScope.launch {
            getCitiesUseCase(countryId).collect { resource ->
                _citiesResponse.value = resource

                when (resource) {
                    is Resource.Success -> {
                        fullCityList = resource.model.data
                        _filteredCities.value = fullCityList
                        _isLoading.value = false
                    }
                    is Resource.Failure -> {
                        _errorMessage.value = resource.exception.message ?: "Unknown error"
                        _isLoading.value = false
                    }
                    is Resource.Progress -> {
                        _isLoading.value = resource.loading
                    }
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _filteredCities.value = if (query.isBlank()) {
            fullCityList
        } else {
            fullCityList.filter { city ->
                city.cityName.contains(query, ignoreCase = true) ||
                        city.districts.any { it.districtName.contains(query, ignoreCase = true) }
            }
        }
    }
}