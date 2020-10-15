package com.educandoweb.couse.services.exceptions;

public class ErroNaoMapeadoException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public ErroNaoMapeadoException (String msg) {
		super(msg);
	}



}