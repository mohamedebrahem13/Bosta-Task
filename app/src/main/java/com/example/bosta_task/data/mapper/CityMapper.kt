package com.example.bosta_task.data.mapper

import com.example.bosta_task.data.models.CitiesResponseDto
import com.example.bosta_task.data.models.CityDto
import com.example.bosta_task.data.models.DistrictDto
import com.example.bosta_task.domain.models.CitiesResponse
import com.example.bosta_task.domain.models.City
import com.example.bosta_task.domain.models.District

object CityMapper {

    fun mapDistrict(dto: DistrictDto): District {
        return District(
            zoneId = dto.zoneId.orEmpty(),
            zoneName = dto.zoneName.orEmpty(),
            zoneOtherName = dto.zoneOtherName.orEmpty(),
            districtId = dto.districtId.orEmpty(),
            districtName = dto.districtName.orEmpty(),
            districtOtherName = dto.districtOtherName.orEmpty(),
            pickupAvailability = dto.pickupAvailability ?: false,
            dropOffAvailability = dto.dropOffAvailability ?: false,
            coverage = dto.coverage.orEmpty()
        )
    }

    fun mapCity(dto: CityDto): City {
        return City(
            cityId = dto.cityId.orEmpty(),
            cityName = dto.cityName.orEmpty(),
            cityOtherName = dto.cityOtherName.orEmpty(),
            cityCode = dto.cityCode.orEmpty(),
            districts = dto.districts?.map(::mapDistrict) ?: emptyList()
        )
    }

    fun mapCitiesResponse(dto: CitiesResponseDto): CitiesResponse {
        return CitiesResponse(
            success = dto.success ?: false,
            message = dto.message.orEmpty(),
            data = dto.data?.map(::mapCity) ?: emptyList()
        )
    }
}