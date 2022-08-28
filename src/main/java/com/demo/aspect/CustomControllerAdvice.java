package com.demo.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.exception.ErrorDetails;
import com.demo.exception.UserNotAuthenticatedException;
import com.demo.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomControllerAdvice {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> userNotFound(){
		ErrorDetails error = new ErrorDetails();
		error.setMessage("data user tidak ditemukan! ");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(UserNotAuthenticatedException.class)
	public ResponseEntity<ErrorDetails> userNotAuthenticated(){
		ErrorDetails error = new ErrorDetails();
		error.setMessage("data user tidak valid, user tidak terautentikasi!");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
}
