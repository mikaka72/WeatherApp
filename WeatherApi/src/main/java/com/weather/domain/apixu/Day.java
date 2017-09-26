package com.weather.domain.apixu;

import lombok.Data;

@Data
public class Day {

	private String avgtemp_c;

    private String totalprecip_mm;

    private String mintemp_f;

    private String mintemp_c;

    private String totalprecip_in;

    private Condition condition;

    private String uv;

    private String avghumidity;

    private String avgvis_km;

    private String avgtemp_f;

    private String maxwind_mph;

    private String avgvis_miles;

    private String maxwind_kph;

    private String maxtemp_c;

    private String maxtemp_f;
	
}
