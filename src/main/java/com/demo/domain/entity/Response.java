package com.demo.domain.entity;

import lombok.Data;

@Data
public class Response {
	
	private String message;
	
	private boolean success;
	
	private Object data;
	
	public Response(String message, boolean success, Object data) {
		this.message = message;
		this.success = success;
		this.data = data;
	}
}
