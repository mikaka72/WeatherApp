package com.weather.repository.apixu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.weather.client.WeatherClient;
import com.weather.dao.WeatherDao;
import com.weather.domain.Weather;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ApixuWeatherDao implements WeatherDao {

	@Autowired
	WeatherClient weatherClient;
	
	@Override
	public List<Weather> find(String key) {
		log.debug("Get weather from Apixu");
		return weatherClient.getCurrentWeather(key);
	}

	@Override
	public List<Weather> getWeatherForecast(String location, int days) {
		log.debug("Get weather forecast from Apixu");
		return weatherClient.getForecast(location, days);
	}

}
