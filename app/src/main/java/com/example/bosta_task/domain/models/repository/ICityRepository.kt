package com.example.bosta_task.domain.models.repository

import com.example.bosta_task.domain.models.CitiesResponse

interface ICityRepository {
    suspend fun getAllDistricts(countryId: String): CitiesResponse
}