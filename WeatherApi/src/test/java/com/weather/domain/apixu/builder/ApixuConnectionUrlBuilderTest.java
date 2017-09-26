package com.weather.domain.apixu.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class ApixuConnectionUrlBuilderTest {

	private  ApixuConnectionUrlBuilder apixuConnectionUrlBuilder;
	
	@Before
	public void init() {
		apixuConnectionUrlBuilder = new ApixuConnectionUrlBuilder();
		org.springframework.test.util.ReflectionTestUtils.setField(apixuConnectionUrlBuilder, "apiKey", 
				"774f5ba8a80e4359");
		
		org.springframework.test.util.ReflectionTestUtils.setField(apixuConnectionUrlBuilder, "currentWeatherEndpoint", 
				"http://api.apixu.com/v1/current.xml?");
		
		org.springframework.test.util.ReflectionTestUtils.setField(apixuConnectionUrlBuilder, "weatherforecastendpoint", 
				"http://api.apixu.com/v1/forecast.xml?");
		
		
	}
	
	@Test
	public void testGetCurrentWeatherUrl() throws Exception {
		
		assertEquals("http://api.apixu.com/v1/current.xml?key=774f5ba8a80e4359&q=oulu", 
				apixuConnectionUrlBuilder.getCurrentWeahterUrl("oulu"));
		
	}
	
	@Test
	public void testGetWeatherForecastUrl() throws Exception {
		
		assertEquals("http://api.apixu.com/v1/forecast.xml?key=774f5ba8a80e4359&q=oulu&days=5", 
				apixuConnectionUrlBuilder.getWeahterForecastUrl("oulu", 5));
		
	}
	
}
