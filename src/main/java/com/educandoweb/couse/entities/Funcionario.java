package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
	@NotNull
	private String dataNascimento = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

	@NotNull
	private String dataIntegracao = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

	@NotNull
	private String dataCadastro = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

	@NotNull
	private int hierarquia;
	@NotNull
	private int aprovado;
	@NotNull
	private int coordenador;
	
	
	
    @ManyToMany
    @JoinTable(name = "tb_funcionario_skill", 
    joinColumns = @JoinColumn(name = "funcionario_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skills> skill = new HashSet<>();
    

	@ManyToOne
	@JoinColumn(name = "funcao_id")
	private Funcao funcao;
    
	
	@ManyToOne
	@JoinColumn(name = "regiao_id")
	private Regiao regiao;
	
	
	@OneToMany (mappedBy = "funcionario")
	@JsonIgnore
	private List<Pendencia> pendencias = new ArrayList<>();
	
	
	public Funcionario() {
	}


	public Funcionario( String nome, String email, String celular, String senha, String senhaConfirm,
			String descricao, String cpf, int hierarquia, int aprovado, int coordenador, Set<Skills> skill, 
			Funcao funcao, Regiao regiao) {
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
		this.coordenador = coordenador;
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


	


	public String getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getDataIntegracao() {
		return dataIntegracao;
	}


	public void setDataIntegracao(String dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}


	public String getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(String dataCadastro) {
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


	public int getCoordenador() {
		return coordenador;
	}


	public void setCoordenador(int coordenador) {
		this.coordenador = coordenador;
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


	public List<Pendencia> getPendencias() {
		return pendencias;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
