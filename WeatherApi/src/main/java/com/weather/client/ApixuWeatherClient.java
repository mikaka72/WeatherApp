package com.weather.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.weather.domain.Weather;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.ForecastResponse;
import com.weather.domain.apixu.converted.EpochTimeConverter;
import com.weather.domain.apixu.converter.ApixuDateConverter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApixuWeatherClient implements WeatherClient{

	private RestTemplate restTemplate;
	

	@Value("${com.weather.service.owm.apikey}")
	private String apiKey;
	
	private String currentWeatherEndpoint = "http://api.apixu.com/v1/current.xml?key=774f5ba8a80e4359bb2123201172009&q=";
	private String forecastEndpoint = "http://api.apixu.com/v1/forecast.xml?key=774f5ba8a80e4359bb2123201172009&q=";
	
	public ApixuWeatherClient() {
		restTemplate = new RestTemplate();
		
	}

	@Override
	public List<Weather> getCurrentWeather(String location) {
		Weather weather = null;

		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());

		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(currentWeatherEndpoint+location, String.class))
				.orElseThrow(RuntimeException::new);
		XStream xstream = getXStream();
		xstream.alias("root",CurrentWeatherResponse.class);
		CurrentWeatherResponse response = (CurrentWeatherResponse) xstream.fromXML(responseXml);

		weather  = new Weather(response.getLocation().getName(), 
				response.getCurrent().getTemp_c(),
				response.getCurrent().getWind_kph(), 
				response.getCurrent().getWind_dir(),
				response.getCurrent().getCondition().getIcon(),
				response.getLocation().getLocaltime());
		
		List<Weather> weathers = new ArrayList<>();
		weathers.add(weather);
		return weathers;
	}

	@Override
	public List<Weather> getForecast(String location, int days) {
		
		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());
		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(forecastEndpoint+location+"&days="+days, String.class))
				.orElseThrow(RuntimeException::new);
		log.info("Response: " +responseXml);
		XStream xstream = getXStream();
		xstream.registerConverter(new DateConverter("yyyy-MM-dd", new String[]{"yyyy-MM-dd"}));
		xstream.registerConverter( new EpochTimeConverter());
		xstream.alias("root",ForecastResponse.class);
		ForecastResponse response = (ForecastResponse)xstream.fromXML(responseXml);
		/*
		response.getForecast().getForecastday().forEach(forecastday -> {
			log.info(forecastday.getDate().toString());
		});*/
		
		return null;
		
	}
	
	private XStream getXStream() {
		XStream xstream = new XStream();
		// Set default security with only apixu package classes to be deserialized
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypesByWildcard(new String[] {"com.weather.domain.apixu.**"});
		return xstream;
	}
	
	
	
}
