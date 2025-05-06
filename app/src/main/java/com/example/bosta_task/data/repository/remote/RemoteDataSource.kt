package com.example.bosta_task.data.repository.remote

import com.example.bosta_task.data.models.CitiesResponseDto
import com.example.bosta_task.domain.models.repository.remote.IRemoteDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cityApi: CityApi
) : IRemoteDataSource {

    override suspend fun getAllDistricts(countryId: String): CitiesResponseDto {
        return cityApi.getAllDistricts(countryId)
    }
}