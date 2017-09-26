package com.weather.domain.apixu;

import lombok.Data;

@Data
public class ForecastResponse {

	private Location location;

    private Forecast forecast;

    private Current current;
	
}
