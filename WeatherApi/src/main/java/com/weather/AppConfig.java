package com.weather;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import static springfox.documentation.builders.PathSelectors.regex;
import javax.sql.DataSource;

@Configuration
public class AppConfig {


    
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Weather")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/v1/.*"))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Weather REST api")
                .description("Weather REST api documentation").build();
    }
    
}