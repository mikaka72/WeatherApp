package com.weather.domain.apixu;


import lombok.Data;

@Data
public class CurrentWeatherResponse {

	private Location location;

    private Current current;
	
}
