package com.weather.service;

import java.util.List;

import com.weather.domain.Weather;

public interface WeatherService {

	public List<Weather> getCurrentWeather(String location);
	public List<Weather> getWeatherForecast(String location, int days);
	
}
