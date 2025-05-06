package com.example.bosta_task.domain.models.repository.remote

import com.example.bosta_task.data.models.CitiesResponseDto

interface IRemoteDataSource {
    suspend fun getAllDistricts(countryId: String): CitiesResponseDto
}