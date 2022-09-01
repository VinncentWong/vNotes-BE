package com.demo.security.provider;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.demo.security.authentication.JwtAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtProvider implements AuthenticationProvider{
	
	@Value("${SECRET_KEY}")
	private String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = authentication.getPrincipal().toString();
		log.info("token = " + token);
		log.info("secret_key in jwt filter = " + secretKey);
		try {
			Jwts.parser()
							.setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
							.parseClaimsJws(token)
							.getBody();
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			log.info("authenticated!");
			return new JwtAuthentication(token, "", authorities);
		}
		catch(Exception ex) {
			log.info("stack trace = " + ex.toString());
			log.info("error occured in jwt provider, error = " + ex.getMessage());
			return new JwtAuthentication("", "");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		if(authentication.equals(JwtAuthentication.class)) {
			return true;
		} else {
			return false;
		}
	}

}
