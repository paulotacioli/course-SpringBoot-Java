package com.educandoweb.couse.services.exceptions;

public class FuncionarioJaDesligadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public FuncionarioJaDesligadoException(String msg) {
		super(msg);
	}

}
