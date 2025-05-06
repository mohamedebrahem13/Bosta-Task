package com.example.bosta_task.data.repository.remote

import com.example.bosta_task.data.models.CitiesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("cities/getAllDistricts")
    suspend fun getAllDistricts(
        @Query("countryId") countryId: String = "60e4482c7cb7d4bc4849c4d5"
    ): CitiesResponseDto
}