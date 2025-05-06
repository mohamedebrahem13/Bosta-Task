package com.example.bosta_task.domain.intractor

import com.example.bosta_task.common.Resource
import com.example.bosta_task.domain.models.CitiesResponse
import com.example.bosta_task.domain.models.repository.ICityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val cityRepository: ICityRepository
) {
    operator fun invoke(countryId: String): Flow<Resource<CitiesResponse>> = flow {
        emit(Resource.Progress(loading = true))
        try {
            val response = cityRepository.getAllDistricts(countryId)
            emit(Resource.Success(response))
        } catch (exception: Exception) {
            emit(Resource.Failure(exception))
        }
    }
}