package com.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${CLIENT_ID}")
	private String clientId;
	
	@Value("${CLIENT_SECRET}")
	private String clientSecret;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/user/login", "/user/registration");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.oauth2Client()
		.and()
		.csrf()
		.disable()
		.httpBasic()
		.disable()
		.formLogin()
		.disable();
	}
	
	@Bean
	public ClientRegistration registrationClient() {
		ClientRegistration common = CommonOAuth2Provider
										.GOOGLE
										.getBuilder("google")
										.clientId(clientId)
										.clientSecret(clientSecret)
										.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
										.authorizationUri("/vNotes/authentication")
										.scope("https://www.googleapis.com/auth/userinfo.email")
										.build();
		return common;
	}
}
