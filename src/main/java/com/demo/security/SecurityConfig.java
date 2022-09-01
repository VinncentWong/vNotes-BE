//package com.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.demo.config.Config;
//import com.demo.security.filter.JwtFilter;
//
//import lombok.extern.slf4j.Slf4j;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	private final Config config;
//	
//	private final JwtFilter filter;
//	
//	@Autowired
//	public SecurityConfig(Config config, JwtFilter filter) {
//		this.config = config;
//		this.filter = filter;
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors()
//		.and()
////		.oauth2Login((c) -> {
////			c.clientRegistrationRepository(config.repository())
////			.redirectionEndpoint((x) -> x.baseUri("http://localhost:8080/login/oauth2/code/google"));
////		})
//		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//		.authorizeHttpRequests((c) -> {
//			c.mvcMatchers("/checkhealth", "/user/login", "/user/authenticated").permitAll()
//			.mvcMatchers("/user/**").authenticated();
//		})
//		.csrf()
//		.disable()
//		.httpBasic()
//		.disable()
//		.formLogin()
//		.disable();
//	}
//}
