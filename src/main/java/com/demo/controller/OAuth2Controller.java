package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.UserNotAuthenticatedException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OAuth2Controller {
	
	@GetMapping("/login/oauth2/code/google")
	public Authentication getUserDetail(OAuth2AuthenticationToken oauth2) throws UserNotAuthenticatedException{
		Authentication token = SecurityContextHolder.getContext().getAuthentication();
		if(token.isAuthenticated()) {
			log.info("tokens = " + oauth2);
			return token;
		} else {
			throw new UserNotAuthenticatedException();
		}
	}
}
