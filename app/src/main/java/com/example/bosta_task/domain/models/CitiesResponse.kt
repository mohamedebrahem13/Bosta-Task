package com.example.bosta_task.domain.models

data class CitiesResponse(
    val success: Boolean,
    val message: String,
    val data: List<City>
)