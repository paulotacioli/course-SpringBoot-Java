package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "tb_funcionario", uniqueConstraints=
@UniqueConstraint(columnNames={"cpf"}))
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
		
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String celular;
	
	@NotEmpty
	private String senha;	
	
	@NotEmpty
	private String senhaConfirm;
	
	@Column(length = 5000)
	@NotEmpty
	private String descricao;
	
	@Id
	@NotNull
	private Long cpf;
	
	@NotEmpty
	private String dataNascimento = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

	@NotEmpty
	private String dataIntegracao = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

	
	private String dataCadastro = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	
	
	@NotNull
	private int aprovado;
	
	
	@NotNull
	private int coordenador;
	
	@NotEmpty
	private String urlFoto;
	
	@NotEmpty
	private String urlCurriculo;
	
	
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
	
	 
	@ManyToOne
	@JoinColumn(name = "comite_id")
	private Comite comite;
	
	
	@OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
	@JsonIgnore
	private Login login;
	
	
	
	public Funcionario() {
	}
	

	public Funcionario( String nome, String email, String celular, String senha, String senhaConfirm,
			String descricao, Long cpf, int aprovado, int coordenador, String urlFoto, String urlCurriculo,  Set<Skills> skill, 
			Funcao funcao, Regiao regiao, Comite comite) {
		super();
	
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
		this.senhaConfirm = senhaConfirm;
		this.descricao = descricao;
		this.cpf = cpf;
		this.aprovado = aprovado;
		this.coordenador = coordenador;
		this.skill = skill;
		this.funcao = funcao;
		this.regiao = regiao;
		this.comite = comite;
		this.urlCurriculo = urlCurriculo;
		this.urlFoto = urlFoto;
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


	public Comite getComite() {
		return comite;
	}


	public void setComite(Comite comite) {
		this.comite = comite;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public String getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}


	public String getUrlCurriculo() {
		return urlCurriculo;
	}


	public void setUrlCurriculo(String urlCurriculo) {
		this.urlCurriculo = urlCurriculo;
	}
}
