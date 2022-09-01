package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import com.demo.config.Config;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final Config config;
	
	@Autowired
	public SecurityConfig(Config config) {
		this.config = config;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/user/login", "/user/registration");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.oauth2Login((c) -> {
			c.clientRegistrationRepository(config.repository())
			.redirectionEndpoint((x) -> x.baseUri("http://localhost:8080/login/oauth2/code/google"));
		})
		.authorizeRequests()
		.mvcMatchers("/user/**")
		.authenticated()
		.and()
		.csrf()
		.disable()
		.httpBasic()
		.disable()
		.formLogin()
		.disable();
	}
}
