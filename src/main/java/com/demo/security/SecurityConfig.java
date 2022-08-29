package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.demo.config.Config;

@EnableWebSecurity
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
		.oauth2Login((c) -> { // c type is OAuth2ClientConfigurer<HttpSecurity>
			c.clientRegistrationRepository(config.repository());
		})
		.csrf()
		.disable()
		.httpBasic()
		.disable()
		.formLogin()
		.disable();
	}
}
