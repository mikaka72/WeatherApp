package com.weather.domain.apixu.converter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.weather.client.ApixuWeatherClient;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.ForecastResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApixuResponseConverter {
	
	
	public static CurrentWeatherResponse getCurrentResponse(String xml)  {
		Serializer serializer = new Persister();
		CurrentWeatherResponse response = null;
		try {
			response = serializer.read(CurrentWeatherResponse.class, xml);
		} catch (Exception e) {
			log.error("Error parsing response xml: " , e);
		}
		 
		return response;
	}
	
	
	public static ForecastResponse getForecastResponse(String xml) {
		
		Serializer serializer = new Persister();
		ForecastResponse response = null;
		try {
			response = serializer.read(ForecastResponse.class, xml);
		} catch (Exception e) {
			log.error("Error parsing response xml: " , e);
		}
		return response;
	}
	
}
