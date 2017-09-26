package com.weather.domain.apixu;

import java.util.List;

import lombok.Data;

@Data
public class Forecast {

	private List<Forecastday> forecastday;
	
}
