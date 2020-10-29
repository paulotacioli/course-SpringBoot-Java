package com.educandoweb.couse.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tb_hierarquia")
public class Hierarquia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@NotNull
	private Long relacionamento;
	
	@OneToOne
	@JoinColumn(name = "funcionario_cpf")
	private Funcionario funcionario;
	
	
	@OneToOne
	@JoinColumn(name = "comite_id")
	private Comite comite;
	
	
	public Hierarquia() {
	}


	public Hierarquia(@NotNull Long relacionamento, Funcionario funcionario, Comite comite) {
		super();
		this.relacionamento = relacionamento;
		this.funcionario = funcionario;
		this.comite = comite;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public Long getRelacionamento() {
		return relacionamento;
	}


	public void setRelacionamento(Long relacionamento) {
		this.relacionamento = relacionamento;
	}


	public Comite getComite() {
		return comite;
	}


	public void setComite(Comite comite) {
		this.comite = comite;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
}
