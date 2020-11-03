package com.educandoweb.couse.services.exceptions;

public class ReferenciaInexistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ReferenciaInexistenteException (String msg) {
		super (msg);
	}

	public ReferenciaInexistenteException() {
		// TODO Auto-generated constructor stub
	}
}