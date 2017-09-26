package com.weather.domain.apixu;

import lombok.Data;

@Data
public class Current {

	private String temp_f;

    private Condition condition;

    private String temp_c;

    private String wind_degree;

    private String wind_dir;

    private String wind_kph;

    private String is_day;

    private String pressure_in;

    private String humidity;

    private String vis_km;

    private String pressure_mb;

    private String precip_mm;

    private String wind_mph;

    private String last_updated_epoch;

    private String cloud;

    private String feelslike_f;

    private String last_updated;

    private String feelslike_c;

    private String precip_in;

    private String vis_miles;
	
}
