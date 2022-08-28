package com.demo.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class Config {
	
	@Value("${URL}")
	private String url;
	
	@Value("${NAME}")
	private String name;
	
	@Value("${PASSWORD}")
	private String password;
	
	@Bean
	public BCryptPasswordEncoder bcrypt() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfiguration cors() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedHeaders(List.of("*"));
		cors.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"));
		cors.setAllowedOrigins(List.of("http://localhost:4200"));
		return cors;
	}
	
	@Bean
	public DataSource dataSourceConfiguration() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(name);
		dataSource.setPassword(password);
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setConnectionTimeout(1000); // nentuin waktu buat nyuruh hikari nunggu sampai dia tau kalau gak bakal bisa konek
		return dataSource;
	}
	
}
