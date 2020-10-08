package com.educandoweb.couse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class CourseApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}



	@Override  
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","DELETE", "OPTIONS")
	    .allowedHeaders( "*" )
        .allowCredentials( false )
        .exposedHeaders( "Authorization" )
		.maxAge( 3600 );
		
		
	}
	
}
