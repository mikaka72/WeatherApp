package com.weather.domain.apixu;

import lombok.Data;

@Data
public class Hour {

	private String will_it_rain;

    private String wind_kph;

    private String windchill_f;

    private String windchill_c;

    private String is_day;

    private String pressure_in;

    private String dewpoint_c;

    private String time;

    private String heatindex_c;

    private String vis_km;

    private String pressure_mb;

    private String heatindex_f;

    private String vis_miles;

    private String temp_f;

    private Condition condition;

    private String wind_degree;

    private String temp_c;

    private String time_epoch;

    private String wind_dir;

    private String dewpoint_f;

    private String humidity;

    private String will_it_snow;

    private String chance_of_rain;

    private String precip_mm;

    private String wind_mph;

    private String feelslike_f;

    private String cloud;

    private String feelslike_c;

    private String chance_of_snow;

    private String precip_in;
	
}
