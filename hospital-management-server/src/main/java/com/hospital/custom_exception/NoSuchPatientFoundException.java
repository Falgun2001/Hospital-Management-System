package com.hospital.custom_exception;

public class NoSuchPatientFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchPatientFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
