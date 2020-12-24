package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_comite", uniqueConstraints=
@UniqueConstraint(columnNames={"comite"}))

public class Comite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String comite;
	
	@Column(length = 5000)
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String dataCriacao = new SimpleDateFormat("dd/MM/yyyy").format(new Date());


	@ManyToOne
	@JoinColumn(name = "regiao_id")
	private Regiao regiao;
	
	@NotEmpty
	private String urlFoto;
	
	
	@OneToMany(mappedBy = "comite")
	@JsonIgnore
	private List<Funcionario> funcionario = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "comite")
@JsonIgnore
	private List<Objetivo> objetivo = new ArrayList<>();
	
	
	public Comite() {
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<Funcionario> getFuncionario() {
		return funcionario;
	}


	public String getComite() {
		return comite;
	}


	public void setComite(String comite) {
		this.comite = comite;
	}


	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
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


	public Regiao getRegiao() {
		return regiao;
	}


	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}


	public String getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}


	public List<Objetivo> getObjetivo() {
		return objetivo;
	}


	public void setObjetivo(List<Objetivo> objetivo) {
		this.objetivo = objetivo;
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
		Comite other = (Comite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
