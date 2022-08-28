package com.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.RegistrationDto;
import com.demo.domain.entity.Response;
import com.demo.domain.entity.Role;
import com.demo.domain.entity.User;
import com.demo.exception.UserNotFoundException;
import com.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	private final UserRepository repo;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Autowired
	public UserService(UserRepository repo, BCryptPasswordEncoder bcrypt) {
		this.repo = repo;
		this.bcrypt = bcrypt;
	}
	
	public ResponseEntity<Response> registerUser(RegistrationDto dto){
		User user = new User();
		user.setName(dto.getUsername());
		user.setPassword(bcrypt.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		user.setRole(Role.USER);
		User result = repo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("success create user", true, result, null));
	}
	
	public ResponseEntity<Response> loginUser(LoginDto dto) throws UserNotFoundException{
		Optional<User> optUser = repo.getUserByEmail(dto.getEmail());
		if(optUser.isEmpty()) {
			throw new UserNotFoundException();
		}
		User user = optUser.get();
		if(bcrypt.matches(dto.getPassword(), user.getPassword())) {}
		return null;
	}
	
}
