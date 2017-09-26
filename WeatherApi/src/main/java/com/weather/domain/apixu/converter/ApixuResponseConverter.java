package com.weather.domain.apixu.converter;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Component;

import com.weather.client.apixu.ApixuWeatherClient;
import com.weather.domain.Weather;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.ForecastResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApixuResponseConverter {
	
	
	public  CurrentWeatherResponse getCurrentResponse(String xml)  {
		Serializer serializer = new Persister();
		CurrentWeatherResponse response = null;
		try {
			response = serializer.read(CurrentWeatherResponse.class, xml);
		} catch (Exception e) {
			log.error("Error parsing response xml: " , e);
		}
		 
		return response;
	}
	
	
	public  ForecastResponse getForecastResponse(String xml) {
		
		Serializer serializer = new Persister();
		ForecastResponse response = null;
		try {
			response = serializer.read(ForecastResponse.class, xml);
		} catch (Exception e) {
			log.error("Error parsing response xml: " , e);
		}
		return response;
	}
	
	public List<Weather> getCurrentWeatherList(CurrentWeatherResponse response){
		
		Weather weather  = new Weather(response.getLocation().getName(), 
				response.getCurrent().getTemp_c(),
				response.getCurrent().getWind_kph(), 
				response.getCurrent().getWind_dir(),
				response.getCurrent().getCondition().getIcon(),
				response.getLocation().getLocaltime());
		
		List<Weather> weathers = new ArrayList<>();
		weathers.add(weather);
		return weathers;
	}
	
	public List<Weather> getForecastList(ForecastResponse response){
		List<Weather> weathers = new ArrayList<>();
		String name = response.getLocation().getName();
		
		response.getForecast().getForecastday().forEach(forecastday -> {
			forecastday.getHour().forEach(hour ->{
				weathers.add(new Weather(name, 
						hour.getTemp_c(), 
						hour.getWind_kph(), 
						hour.getWind_dir(), 
						hour.getCondition().getIcon(), 
						hour.getTime()));
			});
		});
		
		return weathers;
	}
	
}
