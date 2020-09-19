package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String celular;
	private String senha;
	private String senhaConfirm;
	private String descricao;
	private Long cpf;
	private Date dataNascimento;
	private Date dataIntegracao;
	private Date dataCadastro;
	private int hierarquia;
	private int aprovado;
	
	public User() {
		
	}

	public User(Long id, String nome, String email, String celular, String senha, String senhaConfirm, String descricao, Long cpf, Date dataNascimento, Date dataIntegracao, Date dataCadastro, int hierarquia, int aprovado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
		this.senhaConfirm = senhaConfirm;
		this.descricao = descricao;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataIntegracao = dataIntegracao;
		this.dataCadastro = dataCadastro;
		this.hierarquia = hierarquia;
		this.aprovado = aprovado;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", email=" + email + ", celular=" + celular + ", senha=" + senha
				+ ", senhaConfirm=" + senhaConfirm + ", descricao=" + descricao + ", cpf=" + cpf + ", hierarquia="
				+ hierarquia + ", aprovado=" + aprovado + "]";
	}

	public User(Long id, String nome, String email, String celular, String senha,
			String senhaConfirm, String descricao, Long cpf, int hierarquia, int aprovado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
		this.senhaConfirm = senhaConfirm;
		this.descricao = descricao;
		this.cpf = cpf;
		
		this.hierarquia = hierarquia;
		this.aprovado = aprovado;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaConfirm() {
		return senhaConfirm;
	}

	public void setSenhaConfirm(String senhaConfirm) {
		this.senhaConfirm = senhaConfirm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataIntegracao() {
		return dataIntegracao;
	}

	public void setDataIntegracao(Date dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getHierarquia() {
		return hierarquia;
	}

	public void setHierarquia(int hierarquia) {
		this.hierarquia = hierarquia;
	}

	public int getAprovado() {
		return aprovado;
	}

	public void setAprovado(int aprovado) {
		this.aprovado = aprovado;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
