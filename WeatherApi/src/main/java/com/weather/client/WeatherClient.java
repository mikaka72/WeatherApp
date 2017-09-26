package com.weather.client;

import java.util.List;

import com.weather.domain.Weather;

public interface WeatherClient {

	public List<Weather> getCurrentWeather(String location);
	
	public List<Weather> getForecast(String location, int days);
	
}
