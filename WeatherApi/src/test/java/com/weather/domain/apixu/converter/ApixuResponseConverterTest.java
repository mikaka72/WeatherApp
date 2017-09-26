package com.weather.domain.apixu.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.weather.domain.Weather;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.ForecastResponse;
import com.weather.domain.apixu.Forecastday;

public class ApixuResponseConverterTest {
	
	private  ApixuResponseConverter apixuResponseConverter;
	
	@Before
	public void init() {
		apixuResponseConverter = new ApixuResponseConverter();
	}
	
	@Test
	public void testConvertCurrent() throws Exception {
		String xml = getXml("Current.xml");
		
		//CurrentWeatherResponse response = ApixuResponseConverter.getCurrentResponse(xml);
		CurrentWeatherResponse response = apixuResponseConverter.getCurrentResponse(xml);
		assertEquals("oulu", response.getLocation().getName().toLowerCase());
		assertEquals("finland", response.getLocation().getCountry().toLowerCase());
		assertEquals("8", response.getCurrent().getTemp_c());
		assertEquals("2017-09-26 14:32", response.getLocation().getLocaltime());
		assertEquals("S", response.getCurrent().getWind_dir());
		assertEquals("6.1", response.getCurrent().getWind_kph());
		assertEquals("//cdn.apixu.com/weather/64x64/day/143.png", response.getCurrent().getCondition().getIcon());
		
		List<Weather> weathers = apixuResponseConverter.getCurrentWeatherList(response);
		assertEquals(1, weathers.size());
		Weather w = weathers.get(0);
		assertEquals("oulu", w.getLocation().toLowerCase());
		assertEquals("8", w.getTempature());
		assertEquals("2017-09-26 14:32", w.getWeatherDate());
		assertEquals("S", w.getWindfrom());
		assertEquals("6.1", w.getWind());
		assertEquals("//cdn.apixu.com/weather/64x64/day/143.png", w.getIcon());
		
	}
	
	@Test
	public void testConvertCurrentWithInvalidXML() throws Exception {
		String xml = "<root><bogus><bogus></root>";
		
		assertNull(apixuResponseConverter.getCurrentResponse(xml));
		
	}
	
	@Test
	public void testConvertForecast() throws Exception {
		String xml = getXml("Forecast.xml");
		
		ForecastResponse response = apixuResponseConverter.getForecastResponse(xml);
		
		assertEquals(2, response.getForecast().getForecastday().size());
		
		List<Forecastday> days = response.getForecast().getForecastday();
		
		days.forEach(forecastday -> {
			forecastday.getHour().forEach(hour ->{
				assertTrue(!hour.getTemp_c().isEmpty());
				
			});
		});
		
		List<Weather> weathers = apixuResponseConverter.getForecastList(response);
		
		weathers.forEach(weather->{
			assertEquals("oulu", weather.getLocation().toLowerCase());
			assertTrue( !weather.getTempature().isEmpty());
			assertTrue( !weather.getIcon().isEmpty());
			assertTrue( !weather.getWeatherDate().isEmpty());
			assertTrue( !weather.getWind().isEmpty());
			assertTrue( !weather.getWindfrom().isEmpty());
		});
		
	}
	
	@Test
	public void testConvertForecastWithInvalidXML() throws Exception {
		String xml = "<root><bogus><bogus></root>";
		
		assertNull(apixuResponseConverter.getForecastResponse(xml));
		
	}
	
	
	private String getXml(String filename) throws URISyntaxException, IOException {
		File file = ResourceUtils.getFile(this.getClass().getResource("/"+filename));
		String content = new String(Files.readAllBytes(file.toPath()));		 
		return content;
	}

}


