package com.weather.client;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.weather.domain.Weather;
import com.weather.domain.apixu.CurrentWeatherResponse;
import com.weather.domain.apixu.ForecastResponse;
import com.weather.domain.apixu.builder.ApixuConnectionUrlBuilder;
import com.weather.domain.apixu.converter.ApixuResponseConverter;

@Component
public class ApixuWeatherClient implements WeatherClient{

	private RestTemplate restTemplate;
	
	private final ApixuResponseConverter apixuResponseConverter;
	private final ApixuConnectionUrlBuilder apixuConnectionUrlBuilder;

	private String proxyUrl="";
	private int port;
	

	
	public ApixuWeatherClient(final ApixuResponseConverter apixuResponseConverter, 
			ApixuConnectionUrlBuilder apixuConnectionUrlBuilder,
			@Value("${com.weather.service.apixu.proxy.url}") String proxyUrl,
			@Value("${com.weather.service.apixu.proxy.port}") int port
			) {
		this.apixuResponseConverter = apixuResponseConverter;
		this.apixuConnectionUrlBuilder = apixuConnectionUrlBuilder;
		this.proxyUrl = proxyUrl;
		this.port = port;
		
		restTemplate = getRestTemplate();
	}

	@Override
	public List<Weather> getCurrentWeather(String location) {
		Weather weather = null;

		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());

		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(apixuConnectionUrlBuilder.getCurrentWeahterUrl(location), String.class))
				.orElseThrow( () -> new RuntimeException("No response"));
		
		CurrentWeatherResponse response = Optional.ofNullable(apixuResponseConverter.getCurrentResponse(responseXml)).
				orElseThrow( () -> new RuntimeException("Could not deserialize response xml"));
		
		return apixuResponseConverter.getCurrentWeatherList(response);
	}

	@Override
	public List<Weather> getForecast(String location, int days) {
		
		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter());
		String responseXml = Optional.ofNullable(
				restTemplate.getForObject(apixuConnectionUrlBuilder.getWeahterForecastUrl(location, days), String.class))
				.orElseThrow( () -> new RuntimeException("No response"));
		
		ForecastResponse response = Optional.ofNullable(apixuResponseConverter.getForecastResponse(responseXml)).
			orElseThrow( () -> new RuntimeException("Could not deserialize response xml"));
		
		return apixuResponseConverter.getForecastList(response);
		
	}
	
	private RestTemplate getRestTemplate() {
		RestTemplate template = null;
		if(!proxyUrl.isEmpty()) {
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		    Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress(proxyUrl, port));
		    requestFactory.setProxy(proxy);
		    template = new RestTemplate(requestFactory);
		} else {
			template = new RestTemplate();
		} 
		return template;
	}
	
}
