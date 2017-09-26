package com.weather.domain.apixu;

import lombok.Data;

@Data
public class Location {
	
	private String region;
	private String localtime_epoch;
	private String localtime;
	private String lon;
	private String tz_id;
	private String name;
	private String lat;
	private String country;
	
}
