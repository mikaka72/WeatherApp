package com.weather.domain.apixu;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;

@Data
public class Forecastday {

	private Astro astro;

    private Day day;

    private Hour[] hour;

    private Date date;

    private String date_epoch;
	
}
	