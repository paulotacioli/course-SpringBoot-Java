package com.educandoweb.couse.services.exceptions;

public class ViolationException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public ViolationException (Object id, String msg) {
		super(msg);
	}



}