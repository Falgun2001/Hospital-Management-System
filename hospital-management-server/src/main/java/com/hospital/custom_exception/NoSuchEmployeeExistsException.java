package com.hospital.custom_exception;

public class NoSuchEmployeeExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchEmployeeExistsException(String msg) {
		super(msg);
	}

}
