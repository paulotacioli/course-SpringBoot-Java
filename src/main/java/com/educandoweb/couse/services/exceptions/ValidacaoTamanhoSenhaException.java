package com.educandoweb.couse.services.exceptions;

public class ValidacaoTamanhoSenhaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ValidacaoTamanhoSenhaException (String msg) {
		super (msg);
	}
}
