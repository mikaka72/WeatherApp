package com.weather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.weather.domain.Weather;
import com.weather.domain.WeatherApiErrorResponse;
import com.weather.domain.WeatherApiException;
import com.weather.service.WeatherService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	// Returning List for single entity endpoint 
	//just because it is easier in the UI to handle both requests without modifying datastructure
	@RequestMapping(method = RequestMethod.GET, value = "/weather/{location}") 
    @ApiOperation(httpMethod = "GET", value = "Returns current weather in given location (name of the city)")
	public List<Weather> getWeather(@PathVariable(value = "location") String location) throws WeatherApiException {
		try {
			return weatherService.getCurrentWeather(location);
		} catch(Exception e){
			throw new WeatherApiException(e, location);
		}
		
		
	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET, value = "/weather/forecast/{location}/{days}") 
    @ApiOperation(httpMethod = "GET", value = "Returns  weather forecast for next given days in given location (name of the city)")
	public List<Weather> getWeatherForecast(@PathVariable(value = "location") String location
			,@PathVariable(value = "days") int  days) throws WeatherApiException {
		
		try {
			return weatherService.getWeatherForecast(location, days);
		} catch(Exception e){
			throw new WeatherApiException(e, location);
		}
		
	}
	
	// Go with simple annotated exception handler as this is only controller in app
	@ExceptionHandler({ WeatherApiException.class })
	public ResponseEntity<WeatherApiErrorResponse> exceptionHandler(WeatherApiException ex) {
	   WeatherApiErrorResponse response = new WeatherApiErrorResponse();
	   response.setMessage("Could not get weather info for " + ex.getLocation()+ ". Please check the location name");
	   response.setErrorCode(HttpStatus.BAD_REQUEST.value());
	   return new ResponseEntity<WeatherApiErrorResponse>(response, HttpStatus.OK);
    }
	
}
