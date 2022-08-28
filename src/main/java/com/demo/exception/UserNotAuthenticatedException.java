package com.demo.exception;

public class UserNotAuthenticatedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1549051042972946857L;
	
	public UserNotAuthenticatedException() {
		super();
	}

	public UserNotAuthenticatedException(String message) {
		super(message);
	}
}
