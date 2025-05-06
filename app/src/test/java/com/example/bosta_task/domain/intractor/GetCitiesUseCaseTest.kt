package com.example.bosta_task.domain.intractor

import app.cash.turbine.test
import com.example.bosta_task.common.Resource
import com.example.bosta_task.domain.models.CitiesResponse
import com.example.bosta_task.domain.models.City
import com.example.bosta_task.domain.models.District
import com.example.bosta_task.domain.models.repository.ICityRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetCitiesUseCaseTest {

    @MockK
    lateinit var repository: ICityRepository

    private lateinit var useCase: GetCitiesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetCitiesUseCase(repository)
    }

    @Test
    fun `should emit Progress then Success with correct data when response is valid`() = runTest {
        // Given
        val mockDistrict = createMockDistrict()
        val mockCity = createMockCity(mockDistrict)
        val response = CitiesResponse(
            success = true,
            message = "Fetched successfully",
            data = listOf(mockCity)
        )

        // When
        coEvery { repository.getAllDistricts("20") } returns response

        // Then
        useCase("20").test {
            val progress = awaitItem()
            assertTrue(progress is Resource.Progress)
            assertTrue((progress as Resource.Progress).loading)

            val success = awaitItem()
            assertTrue(success is Resource.Success)
            assertEquals(response, (success as Resource.Success).model)
            awaitComplete()
        }
    }

    @Test
    fun `should emit Success with correct data when response is valid`() = runTest {
        // Given
        val mockDistrict = createMockDistrict()
        val mockCity = createMockCity(mockDistrict)
        val response = CitiesResponse(
            success = true,
            message = "Fetched successfully",
            data = listOf(mockCity)
        )

        // When
        coEvery { repository.getAllDistricts("20") } returns response

        // Then
        useCase("20").test {
            awaitItem()
            val success = awaitItem()
            assertTrue(success is Resource.Success)
            assertEquals(response, (success as Resource.Success).model)
            awaitComplete()
        }
    }

    @Test
    fun `should emit Progress with loading true before success when response is valid`() = runTest {
        // Given
        val mockDistrict = createMockDistrict()
        val mockCity = createMockCity(mockDistrict)
        val response = CitiesResponse(
            success = true,
            message = "Fetched successfully",
            data = listOf(mockCity)
        )

        // When
        coEvery { repository.getAllDistricts("20") } returns response

        // Then
        useCase("20").test {
            val progress = awaitItem()
            assertTrue(progress is Resource.Progress)
            assertTrue((progress as Resource.Progress).loading)

            val success = awaitItem()
            assertTrue(success is Resource.Success)
            assertEquals(response, (success as Resource.Success).model)
            awaitComplete()
        }
    }

    @Test
    fun `should emit Failure with correct exception when exception is thrown`() = runTest {
        // Given
        val exception = RuntimeException("API failure")

        // When
        coEvery { repository.getAllDistricts("20") } throws exception

        // Then
        useCase("20").test {
            awaitItem()
            val failure = awaitItem()
            assertTrue(failure is Resource.Failure)
            assertEquals("API failure", (failure as Resource.Failure).exception.message)
            awaitComplete()
        }
    }

    private fun createMockDistrict() = District(
        zoneId = "z1",
        zoneName = "Zone 1",
        zoneOtherName = "Z1",
        districtId = "d1",
        districtName = "Nasr City",
        districtOtherName = "NC",
        pickupAvailability = true,
        dropOffAvailability = true,
        coverage = "Bosta"
    )

    private fun createMockCity(mockDistrict: District) = City(
        cityId = "1",
        cityName = "Cairo",
        cityOtherName = "C",
        cityCode = "CAI",
        districts = listOf(mockDistrict)
    )
}