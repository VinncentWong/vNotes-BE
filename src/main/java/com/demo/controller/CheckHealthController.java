package com.demo.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealthController {
	
	@GetMapping("/")
	public String helloWorld(OAuth2AuthenticationToken oauth2) {
		return oauth2.toString();
	}
}
