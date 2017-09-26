package com.weather.domain.apixu;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

@Data
@Root(strict=false)
public class ForecastResponse {
	@Element
	private Location location;
	@Element
    private Forecast forecast;
	@Element
    private Current current;
	
}
