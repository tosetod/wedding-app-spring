package com.weddingorganizer.exceptions;

public class EmailExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9037437007277077843L;
	
	private String email;
	
	public EmailExistsException(String email) {
		super("User with email: '" + email + "' already exists");
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

}
