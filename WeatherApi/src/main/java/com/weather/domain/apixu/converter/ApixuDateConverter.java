package com.weather.domain.apixu.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.thoughtworks.xstream.converters.basic.DateConverter;

public class ApixuDateConverter extends DateConverter{
	
	public static LocalDateTime getWeatherDateTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(dateTime, formatter);
	}

	
	
}
