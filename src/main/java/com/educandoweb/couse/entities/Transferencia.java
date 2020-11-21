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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_tranferencia")

public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private Long cpf;
	
	@Column(length = 5000)
	@NotEmpty
	private String motivo;
	
	@NotNull
	private Funcao funcaoDestino;
	
	
	private String dataTransferencia = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

	
	@NotNull
	private boolean tranferenciaComite;
	
	
	@NotNull
	private boolean trocaDeCadeiras;

	
	private Comite comiteDestino;
	
	
	public Transferencia() {
	}
	

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public String getDataTransferencia() {
		return dataTransferencia;
	}


	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}


	public Long getCpf() {
		return cpf;
	}


	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public Funcao getFuncaoDestino() {
		return funcaoDestino;
	}


	public void setFuncaoDestino(Funcao funcaoDestino) {
		this.funcaoDestino = funcaoDestino;
	}


	public boolean isTranferenciaComite() {
		return tranferenciaComite;
	}


	public void setTranferenciaComite(boolean tranferenciaComite) {
		this.tranferenciaComite = tranferenciaComite;
	}


	public boolean isTrocaDeCadeiras() {
		return trocaDeCadeiras;
	}


	public void setTrocaDeCadeiras(boolean trocaDeCadeiras) {
		this.trocaDeCadeiras = trocaDeCadeiras;
	}


	public Comite getComiteDestino() {
		return comiteDestino;
	}


	public void setComiteDestino(Comite comiteDestino) {
		this.comiteDestino = comiteDestino;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Transferencia other = (Transferencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
