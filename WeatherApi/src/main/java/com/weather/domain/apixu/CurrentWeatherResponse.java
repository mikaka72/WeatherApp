package com.weather.domain.apixu;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("root")
public class CurrentWeatherResponse {

	private Location location;

    private Current current;
	
}
