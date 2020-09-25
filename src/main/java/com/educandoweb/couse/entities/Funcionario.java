package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_funcionario")
public class Funcionario implements Serializable {
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
	private String cpf;
	private Instant dataNascimento;
	private Instant dataIntegracao;
	private Instant dataCadastro;
	private int hierarquia;
	private int aprovado;
	
	
    @ManyToMany
    @JoinTable(name = "tb_funcionario_skill", 
    joinColumns = @JoinColumn(name = "funcionario_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skills> skill = new HashSet<>();
    

	@ManyToOne
	@JoinColumn(name = "funcao_id")
	private Funcao funcao;
	
	
	@OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private Regiao regiao;

	
	public Funcionario() {
	}


	public Funcionario( String nome, String email, String celular, String senha, String senhaConfirm,
			String descricao, String cpf, Instant dataNascimento, Instant dataIntegracao, Instant dataCadastro,
			int hierarquia, int aprovado, Set<Skills> skill, Funcao funcao, Regiao regiao) {
		super();
	
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
		this.skill = skill;
		this.funcao = funcao;
		this.regiao = regiao;
	}


	public Long getId() {
		return id;
	}


	public Funcionario( String nome, String email, String celular, String senha, String senhaConfirm,
		String descricao, String cpf, int hierarquia, int aprovado) {
	super();
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


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Instant getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Instant getDataIntegracao() {
		return dataIntegracao;
	}


	public void setDataIntegracao(Instant dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}


	public Instant getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Instant dataCadastro) {
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


	public Set<Skills> getSkill() {
		return skill;
	}


	public void setSkill(Set<Skills> skill) {
		this.skill = skill;
	}


	public Funcao getFuncao() {
		return funcao;
	}


	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}


	public Regiao getRegiao() {
		return regiao;
	}


	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	
	
}
