package com.demo.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class RegistrationDto {
	
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	@Length(min = 6)
	private String password;
	
	@NotNull
	@NotBlank
	@Length(min = 5)
	private String username;
}
