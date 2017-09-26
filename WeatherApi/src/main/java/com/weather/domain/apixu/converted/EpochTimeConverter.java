package com.weather.domain.apixu.converted;

import java.sql.Date;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EpochTimeConverter implements Converter{

	@Override
	public boolean canConvert(Class arg0) {
		log.info("canConvert " + arg0.getCanonicalName());
		if(arg0.isInstance(Date.class)) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
		
		log.info("marshal " + arg0.toString());
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
		log.info("unmarshal " + arg0.toString());
		return null;
	}

}
