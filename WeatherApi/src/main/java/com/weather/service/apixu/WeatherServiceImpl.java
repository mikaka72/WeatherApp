package com.weather.service.apixu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.weather.dao.WeatherDao;
import com.weather.domain.Weather;
import com.weather.service.WeatherService;

@Component
public class WeatherServiceImpl implements WeatherService {


	@Autowired 
	WeatherDao weatherDao;
	
	@Override
	public List<Weather> getCurrentWeather(String location) {

		return weatherDao.find(location);
	}

	@Override
	public List<Weather> getWeatherForecast(String location, int days) {
		
		return weatherDao.getWeatherForecast(location, days);
	}

	
	
}
