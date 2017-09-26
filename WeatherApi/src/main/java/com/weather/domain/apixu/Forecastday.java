package com.weather.domain.apixu;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;



import lombok.Data;

@Data
@Root(strict=false)
public class Forecastday {
	
	@Element
	private Astro astro;
	@Element
    private Day day;
	@ElementList(inline = true, required = false)
    private List<Hour> hour;
	@Element
    private Date date;
	@Element
    private String date_epoch;
	
}
	