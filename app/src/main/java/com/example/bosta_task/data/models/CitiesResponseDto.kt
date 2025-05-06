package com.example.bosta_task.data.models

import com.google.gson.annotations.SerializedName

data class CitiesResponseDto(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: List<CityDto>? = null
)