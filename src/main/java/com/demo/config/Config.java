package com.demo.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.demo.security.filter.JwtFilter;
import com.demo.security.manager.JwtManager;
import com.demo.security.provider.JwtProvider;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class Config {
	
	@Value("${URL}")
	private String url;
	
	@Value("${NAME}")
	private String name;
	
	@Value("${PASSWORD}")
	private String password;
	
	@Value("${CLIENT_ID}")
	private String clientId;
	
	@Value("${CLIENT_SECRET}")
	private String clientSecret;
	
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
	
	@Bean
	public ClientRegistration registrationClient() {
		log.info("registrasi client berjalan");
		log.info("client id = " + clientId);
		log.info("client secret = " + clientSecret);
		ClientRegistration common = CommonOAuth2Provider
								.GOOGLE
								.getBuilder("google")
								.clientId(clientId)
								.clientSecret(clientSecret)
								.clientName("vNotes")
								.redirectUri("http://localhost:8080/login/oauth2/code/google")
								.build();
		return common;
	}

	@Bean
	public ClientRegistrationRepository repository() {
		return new InMemoryClientRegistrationRepository(registrationClient());
	}
	
	@Bean
	public JwtFilter jwtFilterBean() {
		return new JwtFilter(jwtManagerBean());
	}
	
	@Bean
	public JwtManager jwtManagerBean() {
		return new JwtManager(jwtProviderBean());
	}
	
	@Bean
	public JwtProvider jwtProviderBean() {
		return new JwtProvider();
	}
	
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.addFilterAt(jwtFilterBean(), UsernamePasswordAuthenticationFilter.class)
		.cors()
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.mvcMatchers("/user/login", "/user/registration", "/checkhealth")
		.permitAll()
		.mvcMatchers("/user/**")
		.authenticated()
		.and()
		.formLogin()
		.disable()
		.httpBasic()
		.disable();
		return http.build();
	}
}
