package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_acao")

public class Acao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String acao;
	
	@Column(length = 5000)
	@NotEmpty
	private String descricao;
	
	@Column(length = 5000)
	@NotEmpty
	private String como;
	
	@NotEmpty
	private String dataCriacao = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	
	
	@NotEmpty
	private String dataLimite = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	
	
	@ManyToOne
	@JoinColumn(name = "objetivo_id")
	private Objetivo objetivo;
	
	
	public Acao() {
	}
	

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	

	public String getComo() {
		return como;
	}


	public void setComo(String como) {
		this.como = como;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	public Objetivo getObjetivo() {
		return objetivo;
	}


	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}


	public String getAcao() {
		return acao;
	}


	public void setAcao(String acao) {
		this.acao = acao;
	}


	public String getDataLimite() {
		return dataLimite;
	}


	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acao other = (Acao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
