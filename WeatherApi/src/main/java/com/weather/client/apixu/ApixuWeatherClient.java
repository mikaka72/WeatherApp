package com.weather.client.apixu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.weather.client.WeatherClient;
import com.weather.domain.Weather;
import com.weather.domain.apixu.Current;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.Forecast;
import com.weather.domain.apixu.ForecastResponse;
import com.weather.domain.apixu.Forecastday;
import com.weather.domain.apixu.Location;
import com.weather.domain.apixu.converter.ApixuResponseConverter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApixuWeatherClient implements WeatherClient{

	private RestTemplate restTemplate;
	
	private final ApixuResponseConverter apixuResponseConverter;

	@Value("${com.weather.service.owm.apikey}")
	private String apiKey;
	
	private String currentWeatherEndpoint = "http://api.apixu.com/v1/current.xml?key=774f5ba8a80e4359bb2123201172009&q=";
	private String forecastEndpoint = "http://api.apixu.com/v1/forecast.xml?key=774f5ba8a80e4359bb2123201172009&q=";
	
	public ApixuWeatherClient(final ApixuResponseConverter apixuResponseConverter) {
		restTemplate = new RestTemplate();
		this.apixuResponseConverter = apixuResponseConverter;
		
	}

	@Override
	public List<Weather> getCurrentWeather(String location) {
		Weather weather = null;

		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());

		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(currentWeatherEndpoint+location, String.class))
				.orElseThrow( () -> new RuntimeException("No response"));
		
		CurrentWeatherResponse response = Optional.ofNullable(apixuResponseConverter.getCurrentResponse(responseXml)).
				orElseThrow( () -> new RuntimeException("Could not deserialize response xml"));
		
		return apixuResponseConverter.getCurrentWeatherList(response);
	}

	@Override
	public List<Weather> getForecast(String location, int days) {
		
		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());
		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(forecastEndpoint+location+"&days="+days, String.class))
				.orElseThrow( () -> new RuntimeException("No response"));
		
		ForecastResponse response = Optional.ofNullable(apixuResponseConverter.getForecastResponse(responseXml)).
			orElseThrow( () -> new RuntimeException("Could not deserialize response xml"));
		
		return apixuResponseConverter.getForecastList(response);
		
	}
	
}
