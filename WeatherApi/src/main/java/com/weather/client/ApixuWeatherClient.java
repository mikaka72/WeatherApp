package com.weather.client;

import java.util.List;
import java.util.Optional;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.weather.domain.Weather;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.ForecastResponse;
import com.weather.domain.apixu.builder.ApixuConnectionUrlBuilder;
import com.weather.domain.apixu.converter.ApixuResponseConverter;

@Component
public class ApixuWeatherClient implements WeatherClient{

	private RestTemplate restTemplate;
	
	private final ApixuResponseConverter apixuResponseConverter;
	private final ApixuConnectionUrlBuilder apixuConnectionUrlBuilder;

	public ApixuWeatherClient(final ApixuResponseConverter apixuResponseConverter, 
			ApixuConnectionUrlBuilder apixuConnectionUrlBuilder) {
		restTemplate = new RestTemplate();
		this.apixuResponseConverter = apixuResponseConverter;
		this.apixuConnectionUrlBuilder = apixuConnectionUrlBuilder;
	}

	@Override
	public List<Weather> getCurrentWeather(String location) {
		Weather weather = null;

		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());

		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(apixuConnectionUrlBuilder.getCurrentWeahterUrl(location), String.class))
				.orElseThrow( () -> new RuntimeException("No response"));
		
		CurrentWeatherResponse response = Optional.ofNullable(apixuResponseConverter.getCurrentResponse(responseXml)).
				orElseThrow( () -> new RuntimeException("Could not deserialize response xml"));
		
		return apixuResponseConverter.getCurrentWeatherList(response);
	}

	@Override
	public List<Weather> getForecast(String location, int days) {
		
		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());
		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(apixuConnectionUrlBuilder.getWeahterForecastUrl(location, days), String.class))
				.orElseThrow( () -> new RuntimeException("No response"));
		
		ForecastResponse response = Optional.ofNullable(apixuResponseConverter.getForecastResponse(responseXml)).
			orElseThrow( () -> new RuntimeException("Could not deserialize response xml"));
		
		return apixuResponseConverter.getForecastList(response);
		
	}
	
}
