package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	public MovieNotFound(String errorMessage) {
		super(errorMessage);
		this.errorMessage=errorMessage;
	}
	
	
	public MovieNotFound() {
		super();
		
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}
