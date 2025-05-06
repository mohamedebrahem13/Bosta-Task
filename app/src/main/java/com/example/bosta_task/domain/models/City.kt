package com.example.bosta_task.domain.models

data class City(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<District>
)