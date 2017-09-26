package com.weather.dao;

import java.util.List;

public interface WeatherApiDao <ENTITY, KEY> {

	List<ENTITY> find(KEY key);
	
}
