package com.weather.domain.apixu.builder;


import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApixuConnectionUrlBuilder {

	@Value("${com.weather.service.apixu.apikey}")
	private String apiKey;
	
	@Value("${com.weather.service.apixu.currentweatherendpoint}")
	private String currentWeatherEndpoint;
	
	@Value("${com.weather.service.apixu.weatherforecastendpoint}")
	private String weatherforecastendpoint;
	
	
	public String getCurrentWeahterUrl(String location) {
		StringBuilder builder = new StringBuilder();
		builder.append(currentWeatherEndpoint).append("key=").append(apiKey).append("&q=").append(location);
		return builder.toString();
	}
	
	public String getWeahterForecastUrl(String location, int days) {
		StringBuilder builder = new StringBuilder();
		builder.append(weatherforecastendpoint).append("key=").append(apiKey).append("&q=").append(location).append("&days=")
		.append(days);
		return builder.toString();
	}
	
}
