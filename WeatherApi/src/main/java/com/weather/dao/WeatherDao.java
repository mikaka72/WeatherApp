package com.weather.dao;

import java.util.List;

import com.weather.domain.Weather;

public interface WeatherDao extends WeatherApiDao<Weather, String> {
	public List<Weather> getWeatherForecast(String location, int days);
}
