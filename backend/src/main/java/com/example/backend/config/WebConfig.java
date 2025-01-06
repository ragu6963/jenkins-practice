package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${CLIENT_ORIGIN}")
  private String CLIENT_ORIGIN;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:5173", CLIENT_ORIGIN) 
				.allowedMethods("GET", "POST")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
}