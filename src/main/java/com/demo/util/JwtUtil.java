package com.demo.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.domain.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUtil {
	
	@Value("${SECRET_KEY}")
	private String secretKey;
	
	public String generateToken(User user) {
		Map<String,Object> map = new HashMap<>();
		map.put("username", user.getName());
		map.put("email", user.getEmail());
		map.put("role", user.getRole());
		map.put("exp", new Date(0, 0, 0, 24, 0));
		log.info("secret key = " + secretKey);
		return Jwts
				.builder()
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secretKey.getBytes()))
				.setClaims(map)
				.setExpiration(new Date(0, 0, 0, 24, 0))
				.compact();
	}
}
