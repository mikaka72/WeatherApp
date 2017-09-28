package com.weather.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasKey;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.weather.domain.Weather;
import com.weather.service.WeatherService;

public class WeatherControllerTest {

	@InjectMocks
	WeatherController weatherController;
	
	@Mock
	WeatherService weatherService;
	
	protected MockMvc mockMvc;



	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(weatherController).build();
	}

	@Test
	public void testGetCurrentWeather() throws Exception {

		when(weatherService.getCurrentWeather("oulu")).thenReturn(createMockWeatherList());
		
		mockMvc.perform(get("/api/v1/weather/oulu"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.[0]", hasKey("location")))
		.andExpect(jsonPath("$.[0]", hasKey("tempature")))
		.andExpect(jsonPath("$.[0]", hasKey("wind")))
		.andExpect(jsonPath("$.[0]", hasKey("windfrom")))
		.andExpect(jsonPath("$.[0]", hasKey("icon")))
		.andExpect(jsonPath("$.[0]", hasKey("weatherDate")));
	
	}
	
	@Test
	public void testGetForecastWeather() throws Exception {

		when(weatherService.getWeatherForecast("oulu", 10)).thenReturn(createMockWeatherList());
		
		mockMvc.perform(get("/api/v1/weather/forecast/oulu/10"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.[0]", hasKey("location")))
		.andExpect(jsonPath("$.[0]", hasKey("tempature")))
		.andExpect(jsonPath("$.[0]", hasKey("wind")))
		.andExpect(jsonPath("$.[0]", hasKey("windfrom")))
		.andExpect(jsonPath("$.[0]", hasKey("icon")))
		.andExpect(jsonPath("$.[0]", hasKey("weatherDate")));
	
	}

	@Test
	public void testGetCurrentWeatherFailure() throws Exception {

		when(weatherService.getCurrentWeather("oulu")).thenThrow( new RuntimeException());
		
		mockMvc.perform(get("/api/v1/weather/oulu"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasKey("errorCode")))
		.andExpect(jsonPath("$", hasKey("message")));
	
	}
	
	@Test
	public void testGetForecastWeatherFailure() throws Exception {

		when(weatherService.getWeatherForecast("oulu", 10)).thenThrow( new RuntimeException());
		
		mockMvc.perform(get("/api/v1/weather/forecast/oulu/10"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasKey("errorCode")))
		.andExpect(jsonPath("$", hasKey("message")));
	
	}
	
	private List<Weather> createMockWeatherList(){
		
		Weather weather =  new Weather("oulu", "2", "3", "e" ,"/icon.png", "2017-09-25 18:53");
		List<Weather> weathers = new ArrayList<>();
		weathers.add(weather);
		return weathers;
	}
	
}
