package com.weather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.weather.domain.Weather;
import com.weather.service.WeatherService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/weather/{location}") 
    @ApiOperation(httpMethod = "GET", value = "Returns current weather in given location (name of the city)")
	public List<Weather> getWeather(@PathVariable(value = "location") String location) {
		
		return weatherService.getCurrentWeather(location);
		
	}
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/weather/forecast/{location}/{days}") 
    @ApiOperation(httpMethod = "GET", value = "Returns  weather forecast for next given days in given location (name of the city)")
	public List<Weather> getWeatherForecast(@PathVariable(value = "location") String location
			,@PathVariable(value = "days") int  days) {
		
		return weatherService.getWeatherForecast(location, days);
		
	}
	
}
