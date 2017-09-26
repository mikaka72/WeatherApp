package com.weather.domain.apixu;

import java.util.List;

import org.simpleframework.xml.ElementList;

import lombok.Data;

@Data
public class Forecast {
	@ElementList(inline = true, required = false)
	private List<Forecastday> forecastday;
	
}
