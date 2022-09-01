package com.demo.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.demo.security.provider.JwtProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtManager implements AuthenticationManager{
	
	private final JwtProvider provider;
	
	@Autowired
	public JwtManager(JwtProvider provider) {
		this.provider = provider;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("masuk ke jwt manager");
		return provider.authenticate(authentication);
	}
	
}
