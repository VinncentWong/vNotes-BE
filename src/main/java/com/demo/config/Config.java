package com.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class Config {
	
	@Bean
	public BCryptPasswordEncoder bcrypt() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfiguration cors() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedHeaders(List.of("*"));
		cors.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"));
		cors.setAllowedOrigins(List.of("*"));
		return cors;
	}
}
