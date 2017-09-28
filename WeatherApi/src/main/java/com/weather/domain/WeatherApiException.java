package com.weather.domain;

public class WeatherApiException extends Throwable{

	private String location;
	
	public WeatherApiException(Throwable cause, String location) {
		super(cause);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	
}
