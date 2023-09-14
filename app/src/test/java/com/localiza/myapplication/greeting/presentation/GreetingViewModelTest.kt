package com.localiza.myapplication.greeting.presentation

import com.localiza.myapplication.BaseTest
import com.localiza.myapplication.greeting.domain.Weather
import com.localiza.myapplication.greeting.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GreetingViewModelTest : BaseTest() {
    private lateinit var viewModel: GreetingViewModel
    private val weatherRepository: WeatherRepository = mockk()

    @BeforeEach
    fun before() {
        viewModel = GreetingViewModel(weatherRepository = weatherRepository)
    }

    @DisplayName("When moister is high")
    @Nested
    inner class WithMoisterTests {
        @DisplayName("and the temperature is high then should be summer")
        @Test
        fun testGivenAViewRequestWhenInitFirstViewShouldLoadingOnShowPage() =
            /* runs the test synchronously */
            runTest {
                //given
                val temperature = 40
                val expectedWeather =
                    Weather(isRaining = true, winterIsComing = true, mediumTemperature = 20.0)
                val expectedViewModelResult =
                    GreetingViewState.RainyViewState(seasonName = "Winter", winterIsComing = true)
                coEvery { weatherRepository.getWeather(temperature) } returns expectedWeather

                //when
                viewModel.checkWeather(temperature)

                //then
                coVerify(exactly = 1) {
                    weatherRepository.getWeather(temperature)
                }
                Assertions.assertEquals(expectedViewModelResult, viewModel.state.value)
            }
    }
}