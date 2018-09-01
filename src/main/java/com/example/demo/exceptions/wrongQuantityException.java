package com.example.demo.exceptions;

public class wrongQuantityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public wrongQuantityException(String msg) {
		super(msg);
	}
}
