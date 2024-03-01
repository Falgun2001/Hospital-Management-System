package com.hospital.custom_exception;

public class EmployeeAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
