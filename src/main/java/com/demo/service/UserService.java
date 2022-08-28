package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.RegistrationDto;
import com.demo.domain.entity.Response;
import com.demo.domain.entity.Role;
import com.demo.domain.entity.User;
import com.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	private UserRepository repo;
	
	@Autowired
	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Response> registerUser(RegistrationDto dto){
		User user = new User();
		user.setName(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setRole(Role.USER);
		User result = repo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("success create user", true, result));
	}
	
}
