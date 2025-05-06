package com.example.bosta_task.data.repository

import com.example.bosta_task.data.mapper.CityMapper
import com.example.bosta_task.domain.models.CitiesResponse
import com.example.bosta_task.domain.models.repository.ICityRepository
import com.example.bosta_task.domain.models.repository.remote.IRemoteDataSource
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val remoteDataSource: IRemoteDataSource
) : ICityRepository {

    override suspend fun getAllDistricts(countryId: String): CitiesResponse {
        return CityMapper.mapCitiesResponse(remoteDataSource.getAllDistricts(countryId))
    }

}