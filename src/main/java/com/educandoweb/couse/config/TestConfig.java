package com.educandoweb.couse.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.repositores.FuncaoRepository;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.repositores.RegiaoRepository;
import com.educandoweb.couse.repositores.SkillsRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private SkillsRepository skillsRepository;
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Autowired
	private RegiaoRepository regiaoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
//		Funcao func1 = new Funcao( "Presidente");
//		Funcao func2 = new Funcao("Analista");
//		Funcao func3 = new Funcao("Marketing digital");
//		Funcao func4 = new Funcao("Programador");
//		Funcao func5 = new Funcao("Scrum");
//		funcaoRepository.saveAll(Arrays.asList(func1, func2, func3, func4, func5));
//		
//		
//		Funcionario f1 = new Funcionario( "Maria Clara", "Mariaclara@gmail.com", "111111", "1234", "1234", "Descrição Maria", "cpfMaria", 0, 0, func1);
//		Funcionario f2 = new Funcionario("Paulo Tacioli", "Paulo@gmail.com", "111111", "123", "123", "descricao Paulo", "CpfPaulo", 0, 0, func3);
//		
//
//		
//		Skills o1 = new Skills("habilidade 1");
//		Skills o2 = new Skills("habilidade 2");
//		Skills o3 = new Skills("habilidade 3");
//		
//		skillsRepository.saveAll(Arrays.asList(o1, o2, o3));
//				
//		Regiao r1 = new Regiao("São Paulo", "São José dos Campos");
//		Regiao r2 = new Regiao("São Paulo", "São José dos Campos");
//	
//		regiaoRepository.saveAll(Arrays.asList(r1, r2));
//		funcionarioRepository.saveAll(Arrays.asList(f1, f2));
//		
	}

}
