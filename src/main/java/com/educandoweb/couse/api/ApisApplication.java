package com.educandoweb.couse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class ApisApplication extends SpringBootServletInitializer {

//	@Override  implements WebMvcConfigurer
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//		.allowedMethods("GET","POST","PUT","DELETE", "OPTIONS")
//	    .allowedHeaders( "*" )
//        .allowCredentials( false )
//        .exposedHeaders( "Authorization" )
//		.maxAge( 3600 );
//		
//		
//	}
//	public static void main(String[] args) {
//		SpringApplication.run(ApisApplication.class, args);
//	}
//	

}