package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Value("{CLIENT_ORIGIN}")
  String CLIENT_ORIGIN;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:5173")
        .allowedOrigins(CLIENT_ORIGIN)
				.allowedMethods("GET", "POST")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
}