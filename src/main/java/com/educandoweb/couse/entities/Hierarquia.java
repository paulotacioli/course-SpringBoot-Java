package com.educandoweb.couse.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tb_hierarquia")
public class Hierarquia implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private char relacionamento;
	
	@Id
	private Long funcionario;
	
	
	private Long comite;
	
	
	public Hierarquia() {
	}


	public Hierarquia(char relacionamento, Long funcionario, Long comite) {
		super();
		this.relacionamento = relacionamento;
		this.funcionario = funcionario;
		this.comite = comite;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Long funcionario) {
		this.funcionario = funcionario;
	}


	public Long getComite() {
		return comite;
	}


	public void setComite(Long comite) {
		this.comite = comite;
	}


//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}


	public char getRelacionamento() {
		return relacionamento;
	}


	public void setRelacionamento(char relacionamento) {
		this.relacionamento = relacionamento;
	}
	
	
	
}
