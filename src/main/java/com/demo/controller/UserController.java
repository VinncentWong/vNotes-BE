package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.RegistrationDto;
import com.demo.domain.entity.Response;
import com.demo.exception.UserNotAuthenticatedException;
import com.demo.exception.UserNotFoundException;
import com.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<Response> registration(@RequestBody @Valid RegistrationDto dto){
		return userService.registerUser(dto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody @Valid LoginDto dto) throws UserNotFoundException, UserNotAuthenticatedException{
		return userService.loginUser(dto);
	}
}
