package com.educandoweb.couse.api.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private Long cpf;
	private String senha;

//need default constructor for JSON Parsing
	public JwtRequest() {
	}

	public JwtRequest(Long cpf, String senha) {
		this.setCpf(cpf);
		this.setSenha(senha);
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}