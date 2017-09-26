package com.weather.domain.apixu.converter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

import org.junit.Test;

public class ApixuDateConverterTest {

	@Test
	public void testDateConversion() {
		
		String dateString = "2017-09-25 18:53";
		LocalDateTime weatherTime = ApixuDateConverter.getWeatherDateTime(dateString);
		assertEquals(2017, weatherTime.getYear());
		assertEquals(Month.SEPTEMBER, weatherTime.getMonth());
		assertEquals(25, weatherTime.getDayOfMonth());
		assertEquals(18, weatherTime.getHour());
		assertEquals(53, weatherTime.getMinute());
		
		
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testInvalidDateConversion() {
		
		String dateString = "2017-09-25";
		LocalDateTime weatherTime = ApixuDateConverter.getWeatherDateTime(dateString);
		
	}
	
}
