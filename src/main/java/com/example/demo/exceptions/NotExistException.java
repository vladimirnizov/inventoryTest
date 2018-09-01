package com.example.demo.exceptions;

public class NotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotExistException(String msg) {
		super(msg);
	}
}
