package com.weather.domain;

import lombok.Data;

@Data
public class WeatherApiErrorResponse {
	private int errorCode;
	private String message;
}
