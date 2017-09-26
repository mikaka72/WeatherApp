package com.weather.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Weather {

	private final String location;
	private final String tempature;
	private final String wind;
	private final String windfrom;
	private final String icon;
	private final String weatherDate;
	
	
}
